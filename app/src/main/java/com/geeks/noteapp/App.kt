package com.geeks.noteapp

import android.app.Application
import androidx.room.Room
import com.geeks.noteapp.data.db.AppDatabase
import com.geeks.noteapp.utils.PreferenceHelper

class App : Application() {

    companion object {
        var appDatabase: AppDatabase? = null
        lateinit var sharedPreferences: PreferenceHelper
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = PreferenceHelper(this)
        getInstance()
    }

    private fun getInstance(): AppDatabase? {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "note_database"
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
        return appDatabase
    }
}