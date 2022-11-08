package com.hackinroms.notes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hackinroms.notes.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

  @Query("SELECT * FROM notes")
  fun getNotes(): Flow<List<Note>>

  @Query("SELECT * FROM notes WHERE id = :id")
  suspend fun getNoteById(id: String): Note

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addNote(note: Note)

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun updateNote(note: Note)

  @Query("DELETE FROM notes WHERE id = :id")
  suspend fun removeNoteById(id: String)

  @Delete
  suspend fun removeNote(note: Note)

  @Query("DELETE FROM notes")
  suspend fun removeAllNotes()
}