package com.hackinroms.notes.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackinroms.notes.models.Note
import com.hackinroms.notes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
  //private val noteList = mutableStateListOf<Note>()

  private val _noteList = MutableStateFlow<List<Note>>(emptyList())
  val noteList = _noteList.asStateFlow()

  init {
    viewModelScope.launch(Dispatchers.IO) {
      // can have many processes running under this coroutine scope
      repository
        .getAllNotes()
        .distinctUntilChanged()
        .collect { listOfNotes ->
          if (listOfNotes.isEmpty()) {
            Log.d("NoteViewModel", "No notes found")
          } else {
            _noteList.value = listOfNotes
          }

        }
    }
  }

  fun addNote(note: Note) {
    viewModelScope.launch { repository.addNote(note) }
  }

  fun removeNote(note: Note) {
    viewModelScope.launch { repository.removeNote(note) }
  }

  fun updateNote(note: Note) {
    viewModelScope.launch { repository.updateNote(note) }
  }
}