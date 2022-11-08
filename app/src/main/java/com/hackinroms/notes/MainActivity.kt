package com.hackinroms.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hackinroms.notes.data.NotesDataSource
import com.hackinroms.notes.models.Note
import com.hackinroms.notes.screens.NoteScreen
import com.hackinroms.notes.screens.NoteViewModel
import com.hackinroms.notes.ui.theme.HackinromNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyApp {
        //val noteViewModel: NoteViewModel by viewModels() // still works
        NotesContent()
      }
    }
  }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
  HackinromNotesTheme {
    // A surface container using the 'background' color from the theme
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colors.background
    ) {
      content()
    }
  }
}

@Composable
fun NotesContent(noteViewModel: NoteViewModel = viewModel()) {
  val notesList = noteViewModel.noteList.collectAsState().value

  NoteScreen(
    notes = notesList,
    onAddNote = { noteViewModel.addNote(it) },
    onRemoveNote = { noteViewModel.removeNote(it) }
  )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MyApp {
    val notes = remember {
      mutableStateListOf<Note>()
    }
    notes.addAll(NotesDataSource().loadNotes())

    NoteScreen(
      notes = notes,
      onAddNote = {
        notes.add(it)
      },
      onRemoveNote = {}
    )
  }
}