package com.example.delefede.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * Servicio de API para sincronizar contenido desde GitHub
 * Repositorio: https://github.com/Fedes10/AppFede
 * 
 * Estructura del repo:
 * - noticias/noticias.json
 * - canciones/canciones.json  
 * - peregrinaciones/peregrinaciones.json
 * - eventos/eventos.json
 */
interface GitHubApiService {
    
    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/Fedes10/AppFede/main/"
    }
    
    // Respuestas wrapper para cada tipo de contenido
    data class NoticiasResponse(
        val version: String,
        val ultima_actualizacion: String,
        val noticias: List<com.example.delefede.data.model.Noticia>
    )
    
    data class CancionesResponse(
        val version: String,
        val ultima_actualizacion: String,
        val canciones: List<com.example.delefede.data.model.Cancion>
    )
    
    data class PeregrinacionesResponse(
        val version: String,
        val ultima_actualizacion: String,
        val peregrinaciones: List<com.example.delefede.data.model.Peregrinacion>
    )
    
    data class EventosResponse(
        val version: String,
        val ultima_actualizacion: String,
        val eventos: List<com.example.delefede.data.model.Evento>
    )
    
    // Endpoints para cada tipo de contenido
    @GET("noticias/noticias.json")
    suspend fun getNoticias(): Response<NoticiasResponse>
    
    @GET("canciones/canciones.json")
    suspend fun getCanciones(): Response<CancionesResponse>
    
    @GET("peregrinaciones/peregrinaciones.json")
    suspend fun getPeregrinaciones(): Response<PeregrinacionesResponse>
    
    @GET("eventos/eventos.json")
    suspend fun getEventos(): Response<EventosResponse>
    
    // Descarga de archivos multimedia (imágenes, audio)
    @Streaming
    @GET
    suspend fun downloadFile(@Url fileUrl: String): Response<ResponseBody>
}
