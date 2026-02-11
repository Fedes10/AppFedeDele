package com.example.delefede.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "noticias")
data class Noticia(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    
    @SerializedName("titulo")
    val titulo: String,
    
    @SerializedName("descripcion")
    val descripcion: String,
    
    @SerializedName("contenido")
    val contenido: String = "",
    
    @SerializedName("imagen")
    val imagenUrl: String = "",
    
    @SerializedName("categoria")
    val categoria: String = "General",
    
    @SerializedName("fecha")
    val fecha: String,
    
    @SerializedName("autor")
    val autor: String = "Delegación de Juventud",
    
    // Local storage path for offline image
    val imagenLocal: String? = null,
    
    val sincronizado: Boolean = false
)
