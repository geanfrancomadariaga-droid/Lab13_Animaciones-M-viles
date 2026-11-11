
package com.example.lab13_animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Laboratorio13App()
                }
            }
        }
    }
}

@Composable
fun Laboratorio13App() {
    var pantalla by rememberSaveable {
        mutableIntStateOf(0)
    }

    if (pantalla == 0) {
        MenuPrincipal(
            onSeleccionar = { pantalla = it }
        )
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedButton(
                onClick = { pantalla = 0 },
                modifier = Modifier.padding(12.dp)
            ) {
                Text("← Volver al menú")
            }

            when (pantalla) {
                1 -> Ejercicio1Screen()
                2 -> Ejercicio2Screen()
                3 -> Ejercicio3Screen()
                4 -> Ejercicio4Screen()
                5 -> Text("Videojuego: Pendiente")
            }

        }
    }
}

@Composable
fun MenuPrincipal(
    onSeleccionar: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Laboratorio 13",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Animaciones en Jetpack Compose",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Selecciona el ejercicio que deseas probar."
        )

        val ejercicios = listOf(
            "1. AnimatedVisibility",
            "2. animateColorAsState",
            "3. Tamaño y posición",
            "4. AnimatedContent",
            "5. Videojuego combinado"
        )

        ejercicios.forEachIndexed { index, titulo ->
            Button(
                onClick = { onSeleccionar(index + 1) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(titulo)
            }
        }
    }
}
