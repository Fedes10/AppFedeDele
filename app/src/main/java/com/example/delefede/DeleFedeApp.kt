package com.example.delefede

import android.app.Application
import com.example.delefede.data.local.AppDatabase
import com.example.delefede.data.preferences.SettingsDataStore
import com.example.delefede.data.sync.SyncManager

class DeleFedeApp : Application() {
    
    lateinit var database: AppDatabase
        private set
    
    lateinit var syncManager: SyncManager
        private set
    
    lateinit var settingsDataStore: SettingsDataStore
        private set
    
    override fun onCreate() {
        super.onCreate()
        instance = this
        
        // Initialize database
        database = AppDatabase.getInstance(this)
        
        // Initialize sync manager
        syncManager = SyncManager(this)
        
        // Initialize settings
        settingsDataStore = SettingsDataStore(this)
    }
    
    companion object {
        lateinit var instance: DeleFedeApp
            private set
    }
}
