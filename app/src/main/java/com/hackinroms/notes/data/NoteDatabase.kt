package com.hackinroms.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hackinroms.notes.models.Note
import com.hackinroms.notes.util.DateConverter
import com.hackinroms.notes.util.UUIDConverter

@TypeConverters(DateConverter::class, UUIDConverter::class)
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
  abstract fun noteDao(): NoteDatabaseDao
}