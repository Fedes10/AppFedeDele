package com.example.delefede.data.sync

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.delefede.data.local.AppDatabase
import com.example.delefede.data.model.*
import com.example.delefede.data.remote.GitHubApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

/**
 * Gestor de sincronización de contenido desde GitHub
 * 
 * Sincroniza datos desde https://github.com/Fedes10/AppFede
 * Los JSON contienen arrays de contenido organizados por tipo:
 * - noticias/noticias.json
 * - canciones/canciones.json
 * - peregrinaciones/peregrinaciones.json
 * - eventos/eventos.json
 */
class SyncManager(private val context: Context) {
    
    companion object {
        private const val TAG = "SyncManager"
    }
    
    private val database = AppDatabase.getInstance(context)
    
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        })
        .build()
    
    private val api: GitHubApiService = Retrofit.Builder()
        .baseUrl(GitHubApiService.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GitHubApiService::class.java)
    
    private val _syncState = MutableStateFlow<SyncState>(SyncState.Idle)
    val syncState: StateFlow<SyncState> = _syncState
    
    sealed class SyncState {
        object Idle : SyncState()
        data class Syncing(val progress: Float, val message: String) : SyncState()
        object Success : SyncState()
        data class Error(val message: String) : SyncState()
    }
    
    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
    
    suspend fun syncAll() {
        if (!isNetworkAvailable()) {
            _syncState.value = SyncState.Error("Sin conexión a Internet. Usando datos locales.")
            return
        }
        
        try {
            _syncState.value = SyncState.Syncing(0f, "Sincronizando noticias...")
            syncNoticias()
            
            _syncState.value = SyncState.Syncing(0.25f, "Sincronizando canciones...")
            syncCanciones()
            
            _syncState.value = SyncState.Syncing(0.5f, "Sincronizando peregrinaciones...")
            syncPeregrinaciones()
            
            _syncState.value = SyncState.Syncing(0.75f, "Sincronizando eventos...")
            syncEventos()
            
            _syncState.value = SyncState.Success
            Log.i(TAG, "Sincronización completada exitosamente")
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing: ${e.message}")
            _syncState.value = SyncState.Error("Error al sincronizar: ${e.message}")
        }
    }
    
    private suspend fun syncNoticias() = withContext(Dispatchers.IO) {
        try {
            val response = api.getNoticias()
            if (response.isSuccessful) {
                val noticiasResponse = response.body()
                val noticias = noticiasResponse?.noticias ?: emptyList()
                
                Log.d(TAG, "Recibidas ${noticias.size} noticias (v${noticiasResponse?.version})")
                
                val noticiasConImagenes = noticias.map { noticia ->
                    // Descargar imagen si tiene URL
                    val localImage = if (noticia.imagenUrl.isNotEmpty()) {
                        downloadAndSaveImage(
                            GitHubApiService.BASE_URL + noticia.imagenUrl,
                            "noticias",
                            noticia.id
                        )
                    } else null
                    
                    noticia.copy(
                        imagenLocal = localImage,
                        sincronizado = true
                    )
                }
                
                if (noticiasConImagenes.isNotEmpty()) {
                    database.noticiaDao().insertAllNoticias(noticiasConImagenes)
                    Log.d(TAG, "Guardadas ${noticiasConImagenes.size} noticias en DB")
                }
            } else {
                Log.w(TAG, "Error HTTP al obtener noticias: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing noticias: ${e.message}")
        }
    }
    
    private suspend fun syncCanciones() = withContext(Dispatchers.IO) {
        try {
            val response = api.getCanciones()
            if (response.isSuccessful) {
                val cancionesResponse = response.body()
                val canciones = cancionesResponse?.canciones ?: emptyList()
                
                Log.d(TAG, "Recibidas ${canciones.size} canciones (v${cancionesResponse?.version})")
                
                val cancionesSync = canciones.map { cancion ->
                    cancion.copy(sincronizado = true)
                }
                
                if (cancionesSync.isNotEmpty()) {
                    database.cancionDao().insertAllCanciones(cancionesSync)
                    Log.d(TAG, "Guardadas ${cancionesSync.size} canciones en DB")
                }
            } else {
                Log.w(TAG, "Error HTTP al obtener canciones: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing canciones: ${e.message}")
        }
    }
    
    private suspend fun syncPeregrinaciones() = withContext(Dispatchers.IO) {
        try {
            val response = api.getPeregrinaciones()
            if (response.isSuccessful) {
                val peregrinacionesResponse = response.body()
                val peregrinaciones = peregrinacionesResponse?.peregrinaciones ?: emptyList()
                
                Log.d(TAG, "Recibidas ${peregrinaciones.size} peregrinaciones (v${peregrinacionesResponse?.version})")
                
                val peregrinacionesConImagenes = peregrinaciones.map { peregrinacion ->
                    // Descargar imagen de portada
                    val localImage = if (peregrinacion.imagenPortadaUrl.isNotEmpty()) {
                        downloadAndSaveImage(
                            GitHubApiService.BASE_URL + peregrinacion.imagenPortadaUrl,
                            "peregrinaciones",
                            peregrinacion.id
                        )
                    } else null
                    
                    peregrinacion.copy(
                        imagenPortadaLocal = localImage,
                        sincronizado = true
                    )
                }
                
                if (peregrinacionesConImagenes.isNotEmpty()) {
                    database.peregrinacionDao().insertAllPeregrinaciones(peregrinacionesConImagenes)
                    Log.d(TAG, "Guardadas ${peregrinacionesConImagenes.size} peregrinaciones en DB")
                }
            } else {
                Log.w(TAG, "Error HTTP al obtener peregrinaciones: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing peregrinaciones: ${e.message}")
        }
    }
    
    private suspend fun syncEventos() = withContext(Dispatchers.IO) {
        try {
            val response = api.getEventos()
            if (response.isSuccessful) {
                val eventosResponse = response.body()
                val eventos = eventosResponse?.eventos ?: emptyList()
                
                Log.d(TAG, "Recibidos ${eventos.size} eventos (v${eventosResponse?.version})")
                
                val eventosWithImages = eventos.map { evento ->
                    // Descargar imagen si existe
                    val localImage = if (!evento.imagen.isNullOrEmpty()) {
                        downloadAndSaveImage(
                            GitHubApiService.BASE_URL + evento.imagen,
                            "eventos",
                            evento.id
                        )
                    } else null
                    
                    evento.copy(
                        imagenLocal = localImage,
                        sincronizado = true
                    )
                }
                
                if (eventosWithImages.isNotEmpty()) {
                    database.eventoDao().insertAllEventos(eventosWithImages)
                    Log.d(TAG, "Guardados ${eventosWithImages.size} eventos en DB")
                }
            } else {
                Log.w(TAG, "Error HTTP al obtener eventos: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing eventos: ${e.message}")
        }
    }
    
    /**
     * Descarga el audio de una canción bajo demanda
     */
    suspend fun downloadAudio(cancion: Cancion): String? = withContext(Dispatchers.IO) {
        if (!isNetworkAvailable() || cancion.audioUrl.isEmpty()) return@withContext null
        
        try {
            val fullUrl = GitHubApiService.BASE_URL + cancion.audioUrl
            val response = api.downloadFile(fullUrl)
            
            if (response.isSuccessful) {
                val audioDir = File(context.filesDir, "audios")
                if (!audioDir.exists()) audioDir.mkdirs()
                
                val audioFile = File(audioDir, "${cancion.id}.mp3")
                response.body()?.byteStream()?.use { input ->
                    FileOutputStream(audioFile).use { output ->
                        input.copyTo(output)
                    }
                }
                
                val localPath = audioFile.absolutePath
                database.cancionDao().updateAudioLocal(cancion.id, localPath)
                Log.d(TAG, "Audio descargado: ${cancion.titulo}")
                return@withContext localPath
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error downloading audio: ${e.message}")
        }
        return@withContext null
    }
    
    private suspend fun downloadAndSaveImage(
        imageUrl: String,
        folder: String,
        id: String
    ): String? = withContext(Dispatchers.IO) {
        if (imageUrl.isEmpty()) return@withContext null
        
        try {
            val response = api.downloadFile(imageUrl)
            if (response.isSuccessful) {
                val imageDir = File(context.filesDir, "images/$folder")
                if (!imageDir.exists()) imageDir.mkdirs()
                
                val extension = imageUrl.substringAfterLast(".", "jpg")
                val imageFile = File(imageDir, "$id.$extension")
                
                response.body()?.byteStream()?.use { input ->
                    FileOutputStream(imageFile).use { output ->
                        input.copyTo(output)
                    }
                }
                
                Log.d(TAG, "Imagen guardada: $folder/$id")
                return@withContext imageFile.absolutePath
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error downloading image: ${e.message}")
        }
        return@withContext null
    }
    
    fun getLocalImagePath(folder: String, id: String, extension: String = "jpg"): String? {
        val file = File(context.filesDir, "images/$folder/$id.$extension")
        return if (file.exists()) file.absolutePath else null
    }
    
    fun getLocalAudioPath(id: String): String? {
        val file = File(context.filesDir, "audios/$id.mp3")
        return if (file.exists()) file.absolutePath else null
    }
}
