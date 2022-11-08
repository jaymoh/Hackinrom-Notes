package com.hackinroms.notes.util

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(time: Long): String {
  val date = Date(time)
  val format = SimpleDateFormat(
    "EEE, d MMM yyyy, hh:mm aaa", // EEE, d MMM yyyy
    Locale.getDefault()
  )
  return format.format(date)
}