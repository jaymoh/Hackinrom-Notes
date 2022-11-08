package com.hackinroms.notes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity(tableName = "notes")
data class Note(
  @PrimaryKey
  val id: UUID = UUID.randomUUID(),

  @ColumnInfo(name = "title")
  val title: String,

  @ColumnInfo(name = "content")
  val content: String,

  @ColumnInfo(name = "note_entry_date")
  val entryDate: Date = Date.from(Instant.now())
)
