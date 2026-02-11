package com.example.delefede.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "peregrinaciones")
data class Peregrinacion(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    
    @SerializedName("nombre")
    val nombre: String,
    
    @SerializedName("descripcion")
    val descripcion: String,
    
    @SerializedName("fecha_salida")
    val fechaSalida: String,
    
    @SerializedName("fecha_regreso")
    val fechaRegreso: String,
    
    @SerializedName("precio")
    val precio: String,
    
    @SerializedName("requisitos")
    val requisitos: String = "",
    
    @SerializedName("inscripcion_apertura")
    val inscripcionApertura: String = "",
    
    @SerializedName("inscripcion_cierre")
    val inscripcionCierre: String = "",
    
    @SerializedName("plazas")
    val plazas: String = "Limitadas",
    
    @SerializedName("enlace_inscripcion")
    val enlaceInscripcion: String = "",
    
    @SerializedName("imagen_portada")
    val imagenPortadaUrl: String = "",
    
    @SerializedName("imagenes")
    val imagenesUrls: String = "", // JSON array as string
    
    @SerializedName("color_tema")
    val colorTema: String = "#FF8A50",
    
    // Local storage
    val imagenPortadaLocal: String? = null,
    
    val sincronizado: Boolean = false
)
