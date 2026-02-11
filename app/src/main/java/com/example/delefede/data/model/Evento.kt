package com.example.delefede.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "eventos")
data class Evento(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    
    @SerializedName("titulo")
    val titulo: String,
    
    @SerializedName("descripcion")
    val descripcion: String = "",
    
    @SerializedName("fecha_inicio")
    val fechaInicio: String, // formato: "2026-02-20"
    
    @SerializedName("fecha_fin")
    val fechaFin: String? = null,
    
    @SerializedName("hora_inicio")
    val horaInicio: String? = null, // formato: "18:00"
    
    @SerializedName("hora_fin")
    val horaFin: String? = null,
    
    @SerializedName("ubicacion")
    val ubicacion: String = "",
    
    @SerializedName("tipo")
    val tipo: String = "general", // peregrinacion, vigilia, procesion, convivencia, formacion
    
    @SerializedName("color")
    val color: String = "#FF8A50",
    
    @SerializedName("imagen")
    val imagen: String? = null,
    
    // Ruta local de la imagen descargada
    val imagenLocal: String? = null,
    
    @SerializedName("enlace_noticia")
    val enlaceNoticia: String? = null,
    
    @SerializedName("recordatorio")
    val recordatorio: Boolean = false,
    
    val sincronizado: Boolean = false
)
