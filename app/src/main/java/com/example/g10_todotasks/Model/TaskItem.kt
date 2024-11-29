package com.example.g10_todotasks.Model

import java.time.LocalDate
import java.time.LocalTime

data class TaskItem(
    val dataTitle: String,
    val time: LocalTime?,
    val date: LocalDate?
)