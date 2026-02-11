package com.example.delefede.data.local

import androidx.room.*
import com.example.delefede.data.model.Noticia
import kotlinx.coroutines.flow.Flow

@Dao
interface NoticiaDao {
    
    @Query("SELECT * FROM noticias ORDER BY fecha DESC")
    fun getAllNoticias(): Flow<List<Noticia>>
    
    @Query("SELECT * FROM noticias WHERE categoria = :categoria ORDER BY fecha DESC")
    fun getNoticiasByCategoria(categoria: String): Flow<List<Noticia>>
    
    @Query("SELECT * FROM noticias WHERE id = :id")
    suspend fun getNoticiaById(id: String): Noticia?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoticia(noticia: Noticia)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNoticias(noticias: List<Noticia>)
    
    @Update
    suspend fun updateNoticia(noticia: Noticia)
    
    @Delete
    suspend fun deleteNoticia(noticia: Noticia)
    
    @Query("DELETE FROM noticias")
    suspend fun deleteAllNoticias()
    
    @Query("SELECT COUNT(*) FROM noticias")
    suspend fun getCount(): Int
}
