package com.geeks.noteapp.interfaces

import com.geeks.noteapp.data.models.NoteModel

interface OnClickItem {

    fun onLongClick(noteModel: NoteModel)

    fun onClick(noteModel: NoteModel)
}