package com.geeks.noteapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModels")
data class NoteModel(
    val title: String,
    val description: String,
    val time: String,
    val color: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}