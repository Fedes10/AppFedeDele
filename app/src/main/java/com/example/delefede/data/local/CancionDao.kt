package com.example.delefede.data.local

import androidx.room.*
import com.example.delefede.data.model.Cancion
import kotlinx.coroutines.flow.Flow

@Dao
interface CancionDao {
    
    @Query("SELECT * FROM canciones ORDER BY categoria, titulo")
    fun getAllCanciones(): Flow<List<Cancion>>
    
    @Query("SELECT * FROM canciones WHERE categoria = :categoria ORDER BY titulo")
    fun getCancionesByCategoria(categoria: String): Flow<List<Cancion>>
    
    @Query("SELECT DISTINCT categoria FROM canciones ORDER BY categoria")
    fun getCategorias(): Flow<List<String>>
    
    @Query("SELECT * FROM canciones WHERE id = :id")
    suspend fun getCancionById(id: String): Cancion?
    
    @Query("SELECT * FROM canciones WHERE titulo LIKE '%' || :query || '%' OR artista LIKE '%' || :query || '%'")
    fun searchCanciones(query: String): Flow<List<Cancion>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCancion(cancion: Cancion)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCanciones(canciones: List<Cancion>)
    
    @Update
    suspend fun updateCancion(cancion: Cancion)
    
    @Delete
    suspend fun deleteCancion(cancion: Cancion)
    
    @Query("DELETE FROM canciones")
    suspend fun deleteAllCanciones()
    
    @Query("UPDATE canciones SET audioLocal = :localPath, descargado = 1 WHERE id = :id")
    suspend fun updateAudioLocal(id: String, localPath: String)
    
    @Query("SELECT COUNT(*) FROM canciones")
    suspend fun getCount(): Int
}
