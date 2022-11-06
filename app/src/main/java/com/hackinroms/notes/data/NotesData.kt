package com.hackinroms.notes.data

import com.hackinroms.notes.models.Note

class NotesDataSource {
  fun loadNotes(): List<Note> {
    return listOf(
      Note(
        title = "Something to do",
        content = "This is a note"
      ),
      Note(
        title = "Another",
        content = "This is another note"
      ),
      Note(
        title = "We can do this",
        content = "Think of the possibilities"
      ),
      Note(
        title = "If you can dream it",
        content = "You can do it"
      )

    )
  }
}