package com.example.delefede.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "canciones")
data class Cancion(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    
    @SerializedName("titulo")
    val titulo: String,
    
    @SerializedName("artista")
    val artista: String = "Delegación de Juventud",
    
    @SerializedName("categoria")
    val categoria: String,
    
    @SerializedName("letra")
    val letra: String = "",
    
    @SerializedName("audio")
    val audioUrl: String = "",
    
    @SerializedName("duracion")
    val duracion: Int = 0, // en segundos
    
    // Local storage paths for offline
    val audioLocal: String? = null,
    
    val sincronizado: Boolean = false,
    
    val descargado: Boolean = false
)
