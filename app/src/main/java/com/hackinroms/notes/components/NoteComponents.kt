package com.hackinroms.notes.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
  modifier: Modifier = Modifier,
  text: String,
  label: String,
  maxLines: Int = 1,
  onTextChange: (String) -> Unit,
  onImeAction: () -> Unit = {},
) {
  val keyboardController = LocalSoftwareKeyboardController.current

  TextField(
    value = text,
    onValueChange = onTextChange,
    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
    maxLines = maxLines,
    label = { Text(text = label) },
    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    keyboardActions = KeyboardActions(onDone = {
      onImeAction()
      keyboardController?.hide()
    }),
    modifier = modifier
  )
}

@Composable
fun OutlinedNoteButton(
  modifier: Modifier = Modifier,
  text: String,
  onClick: () -> Unit,
  enabled: Boolean = true,
) {
  OutlinedButton(
    onClick = onClick,
    shape = CircleShape,
    enabled = enabled,
    modifier = modifier,

  ) {
    Text(text = text)
  }
}

@Composable
fun NoteButton(
  modifier: Modifier = Modifier,
  text: String,
  onClick: () -> Unit,
  enabled: Boolean = true,
) {
  Button(
    onClick = onClick,
    shape = CircleShape,
    enabled = enabled,
    modifier = modifier,

    ) {
    Text(text = text)
  }
}
