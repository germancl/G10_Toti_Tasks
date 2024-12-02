package com.example.g10_todotasks.UI

data class Task(
    val id: Int,               // Identificador único de la tarea
    var description: String,   // Descripción de la tarea
    var isCompleted: Boolean  // Estado de la tarea (completada o no)
)
