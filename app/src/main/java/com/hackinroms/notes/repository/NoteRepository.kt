package com.hackinroms.notes.repository

import com.hackinroms.notes.data.NoteDatabaseDao
import com.hackinroms.notes.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
  suspend fun addNote(note: Note) = noteDatabaseDao.addNote(note)
  suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note)
  suspend fun removeNote(note: Note) = noteDatabaseDao.removeNote(note)
  suspend fun removeAllNotes() = noteDatabaseDao.removeAllNotes()
  fun getAllNotes(): Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()
}