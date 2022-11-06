package com.hackinroms.notes.models

import java.time.LocalDateTime
import java.util.UUID

data class Note(
  val id: UUID = UUID.randomUUID(),
  val title: String,
  val content: String,
  val entryDate: LocalDateTime = LocalDateTime.now()
)
