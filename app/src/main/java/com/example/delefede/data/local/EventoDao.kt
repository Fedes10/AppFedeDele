package com.example.delefede.data.local

import androidx.room.*
import com.example.delefede.data.model.Evento
import kotlinx.coroutines.flow.Flow

@Dao
interface EventoDao {
    
    @Query("SELECT * FROM eventos ORDER BY fechaInicio, horaInicio")
    fun getAllEventos(): Flow<List<Evento>>
    
    @Query("SELECT * FROM eventos WHERE fechaInicio = :fecha ORDER BY horaInicio")
    fun getEventosByFecha(fecha: String): Flow<List<Evento>>
    
    @Query("SELECT * FROM eventos WHERE fechaInicio BETWEEN :fechaInicio AND :fechaFin ORDER BY fechaInicio, horaInicio")
    fun getEventosBetweenDates(fechaInicio: String, fechaFin: String): Flow<List<Evento>>
    
    @Query("SELECT * FROM eventos WHERE tipo = :tipo ORDER BY fechaInicio")
    fun getEventosByTipo(tipo: String): Flow<List<Evento>>
    
    @Query("SELECT * FROM eventos WHERE id = :id")
    suspend fun getEventoById(id: String): Evento?
    
    @Query("SELECT DISTINCT fechaInicio FROM eventos ORDER BY fechaInicio")
    fun getFechasConEventos(): Flow<List<String>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvento(evento: Evento)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEventos(eventos: List<Evento>)
    
    @Update
    suspend fun updateEvento(evento: Evento)
    
    @Delete
    suspend fun deleteEvento(evento: Evento)
    
    @Query("DELETE FROM eventos")
    suspend fun deleteAllEventos()
}
