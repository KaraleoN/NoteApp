package com.geeks.noteapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeks.noteapp.data.db.daos.NoteDao
import com.geeks.noteapp.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}