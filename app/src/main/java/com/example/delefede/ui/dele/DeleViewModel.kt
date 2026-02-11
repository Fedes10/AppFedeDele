package com.example.delefede.ui.dele

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.delefede.data.local.AppDatabase
import com.example.delefede.data.model.Cancion
import com.example.delefede.data.sync.SyncManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla del Cancionero.
 * Obtiene TODAS las canciones desde GitHub via SyncManager.
 * No usa datos hardcodeados - todo viene del repositorio.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class DeleViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getInstance(application)
    private val cancionDao = database.cancionDao()
    private val syncManager = SyncManager(application)
    
    // Estado de sincronización
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage
    
    // Filtros
    private val _searchQuery = MutableStateFlow("")
    private val _selectedCategory = MutableStateFlow<String?>(null)
    
    // Canciones desde Room (observa cambios automáticamente)
    val canciones: LiveData<List<Cancion>> = _searchQuery.flatMapLatest { query ->
        _selectedCategory.flatMapLatest { category ->
            when {
                query.isNotEmpty() -> cancionDao.searchCanciones(query)
                category != null -> cancionDao.getCancionesByCategoria(category)
                else -> cancionDao.getAllCanciones()
            }
        }
    }.asLiveData()
    
    // Categorías disponibles
    val categorias: LiveData<List<String>> = cancionDao.getCategorias().asLiveData()
    
    // Canción seleccionada para el mini player
    private val _cancionSeleccionada = MutableLiveData<Cancion?>()
    val cancionSeleccionada: LiveData<Cancion?> = _cancionSeleccionada
    
    init {
        // Sincronizar canciones desde GitHub al iniciar
        sincronizarDesdeGitHub()
    }
    
    /**
     * Sincroniza las canciones desde el repositorio de GitHub.
     */
    fun sincronizarDesdeGitHub() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            try {
                syncManager.syncAll()
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = "Error al sincronizar: ${e.message}"
            }
        }
    }
    
    /**
     * Buscar canciones por texto (título o artista)
     */
    fun buscarCanciones(query: String) {
        _searchQuery.value = query
        _selectedCategory.value = null
    }
    
    /**
     * Filtrar por categoría
     */
    fun filtrarPorCategoria(categoria: String) {
        _selectedCategory.value = categoria
        _searchQuery.value = ""
    }
    
    /**
     * Mostrar todas las canciones (sin filtro)
     */
    fun mostrarTodas() {
        _selectedCategory.value = null
        _searchQuery.value = ""
    }
    
    /**
     * Seleccionar canción para reproducir
     */
    fun seleccionarCancion(cancion: Cancion) {
        _cancionSeleccionada.value = cancion
    }
    
    /**
     * Descargar audio de una canción para reproducción offline
     */
    fun descargarAudio(cancion: Cancion) {
        viewModelScope.launch {
            syncManager.downloadAudio(cancion)
        }
    }
    
    /**
     * Forzar recarga desde GitHub
     */
    fun refrescar() {
        viewModelScope.launch {
            _isLoading.value = true
            syncManager.syncAll()
            _isLoading.value = false
        }
    }
}
