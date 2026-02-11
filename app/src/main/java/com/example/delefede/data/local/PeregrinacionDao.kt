package com.example.delefede.data.local

import androidx.room.*
import com.example.delefede.data.model.Peregrinacion
import kotlinx.coroutines.flow.Flow

@Dao
interface PeregrinacionDao {
    
    @Query("SELECT * FROM peregrinaciones ORDER BY fechaSalida")
    fun getAllPeregrinaciones(): Flow<List<Peregrinacion>>
    
    @Query("SELECT * FROM peregrinaciones WHERE id = :id")
    suspend fun getPeregrinacionById(id: String): Peregrinacion?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeregrinacion(peregrinacion: Peregrinacion)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPeregrinaciones(peregrinaciones: List<Peregrinacion>)
    
    @Update
    suspend fun updatePeregrinacion(peregrinacion: Peregrinacion)
    
    @Delete
    suspend fun deletePeregrinacion(peregrinacion: Peregrinacion)
    
    @Query("DELETE FROM peregrinaciones")
    suspend fun deleteAllPeregrinaciones()
}
