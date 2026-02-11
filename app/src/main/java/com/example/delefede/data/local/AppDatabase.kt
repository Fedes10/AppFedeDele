package com.example.delefede.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.delefede.data.model.Cancion
import com.example.delefede.data.model.Evento
import com.example.delefede.data.model.Noticia
import com.example.delefede.data.model.Peregrinacion

@Database(
    entities = [Noticia::class, Cancion::class, Peregrinacion::class, Evento::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun noticiaDao(): NoticiaDao
    abstract fun cancionDao(): CancionDao
    abstract fun peregrinacionDao(): PeregrinacionDao
    abstract fun eventoDao(): EventoDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "delefede_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
