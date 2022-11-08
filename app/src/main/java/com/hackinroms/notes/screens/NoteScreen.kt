package com.hackinroms.notes.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackinroms.notes.R
import com.hackinroms.notes.components.NoteInputText
import com.hackinroms.notes.components.OutlinedNoteButton
import com.hackinroms.notes.data.NotesDataSource
import com.hackinroms.notes.models.Note
import com.hackinroms.notes.util.formatDate
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
  notes: List<Note>,
  onAddNote: (Note) -> Unit,
  onRemoveNote: (Note) -> Unit,
) {
  val title = remember {
    mutableStateOf("")
  }
  val content = remember {
    mutableStateOf("")
  }

  val context = LocalContext.current


  Column(
    modifier = Modifier.padding(6.dp)
  ) {
    TopAppBar(
      title = {
        Text(text = stringResource(id = R.string.app_name))
      },
      actions = {
        Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
      },
      backgroundColor = MaterialTheme.colors.background,
    )

    // content
    Column(
      modifier = Modifier
        .fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      NoteInputText(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        text = title.value,
        label = "Title",
        onTextChange = {
          if (it.all { char ->
              char.isLetterOrDigit() || char.isWhitespace()
            }) {
            title.value = it
          }
        })

      NoteInputText(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        text = content.value,
        label = "Content",
        maxLines = 5,
        onTextChange = {
          /*if (it.trim().isNotEmpty()) {
            description.value = it
          }*/
          if (it.all { char ->
              char.isLetterOrDigit() || char.isWhitespace()
            }) {
            content.value = it
          }
        })

      OutlinedNoteButton(
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
        text = "Save",
        onClick = {
          if (title.value.isNotEmpty() && content.value.isNotEmpty()) {
            // save to datasource
            onAddNote(
              Note(
                title = title.value,
                content = content.value,
              )
            )

            // clear the fields
            title.value = ""
            content.value = ""

            Toast.makeText(context, "Note saved", Toast.LENGTH_SHORT).show()
          }
        })
    }
    Divider(modifier = Modifier.padding(10.dp))

    NoteList(notes = notes, onRemoveNote = onRemoveNote)

  }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
  NoteScreen(
    notes = NotesDataSource().loadNotes(),
    onAddNote = {},
    onRemoveNote = {}
  )
}

@Composable
fun NoteList(
  notes: List<Note>,
  onRemoveNote: (Note) -> Unit,
) {
  LazyColumn {
    items(notes) { note ->
      NoteItem(
        note = note,
        onNoteItemClicked = onRemoveNote
      )
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItem(
  modifier: Modifier = Modifier,
  note: Note,
  onNoteItemClicked: (Note) -> Unit,
) {
  val surfaceColor =  if  (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray

  Surface(
    modifier = modifier
      .padding(4.dp)
      .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
      .fillMaxWidth(),
    color = surfaceColor,
    elevation = 4.dp,
  ) {
    Column(
      modifier = modifier
        .padding(horizontal = 14.dp, vertical = 8.dp)
        //.clickable { onNoteItemClicked(note) }
        .combinedClickable(
          onClick = { onNoteItemClicked(note) },
          onLongClick = {
            // Todo: add dropdown menu with edit and delete options
            Log.d("NoteScreen", "Long Click")
          }
        ),
      horizontalAlignment = Alignment.Start,
    ) {
      Text(
        text = note.title,
        style = MaterialTheme.typography.h6,
      )
      Text(
        text = note.content,
        style = MaterialTheme.typography.subtitle1,
      )
      Text(
        text = formatDate(note.entryDate.time),
        // EEE, d MMM yyyy
        style = MaterialTheme.typography.caption,
      )

    }

  }
}