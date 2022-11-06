package com.hackinroms.notes.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.hackinroms.notes.data.NotesDataSource
import com.hackinroms.notes.models.Note

class NoteViewModel: ViewModel() {
  private var noteList = mutableStateListOf<Note>()

  init {
    noteList.addAll(NotesDataSource().loadNotes())
  }

  fun addNote(note: Note) {
    noteList.add(note)
  }

  fun removeNote(note: Note) {
    noteList.remove(note)
  }

  fun getNotes(): List<Note> {
    return noteList
  }
}