package com.example.g10_todotasks.UI

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.g10_todotasks.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        // Manejo de padding para soportar "edge-to-edge"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar botón "Atrás"
        val arrBack = findViewById<ImageView>(R.id.arrBack)
        arrBack.setOnClickListener {
            finish() // Cierra la actividad actual y regresa a la anterior
        }

        // Configurar botón FAB para guardar cambios
        val fabDetail = findViewById<FloatingActionButton>(R.id.fabDetail)
        fabDetail.setOnClickListener {
            val writeTask = findViewById<EditText>(R.id.writeTask).text.toString()
            val writeDelDate = findViewById<EditText>(R.id.writeDelDate).text.toString()
            val writeDelTime = findViewById<EditText>(R.id.writeDelTime).text.toString()

            if (writeTask.isNotEmpty()) {
                // Crear un intent para devolver los datos
                val resultIntent = Intent().apply {
                    putExtra("TASK_DESCRIPTION", writeTask)
                    putExtra("TASK_DATE", writeDelDate)
                    putExtra("TASK_TIME", writeDelTime)
                }
                setResult(Activity.RESULT_OK, resultIntent) // Indicar que hay un resultado exitoso
                finish() // Finalizar la actividad
            } else {
                // Mostrar error si no hay descripción
                findViewById<EditText>(R.id.writeTask).error = "La descripción de la tarea no puede estar vacía"
            }
        }
    }
}
