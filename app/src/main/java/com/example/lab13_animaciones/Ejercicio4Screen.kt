
package com.example.lab13_animaciones

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// Estados disponibles
enum class EstadoPantalla {
    CARGANDO,
    CONTENIDO,
    ERROR
}

@Composable
fun Ejercicio4Screen() {

    var estado by remember {
        mutableStateOf(EstadoPantalla.CARGANDO)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            "Ejercicio 4: AnimatedContent",
            style = MaterialTheme.typography.titleMedium
        )

        AnimatedContent(
            targetState = estado,
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(800)
                ) togetherWith fadeOut(
                    animationSpec = tween(500)
                )
            },
            label = "CambioEstado"
        ) { estadoActual ->

            val mensaje = when (estadoActual) {
                EstadoPantalla.CARGANDO ->
                    "Cargando información..."

                EstadoPantalla.CONTENIDO ->
                    "Contenido cargado correctamente"

                EstadoPantalla.ERROR ->
                    "Error al obtener los datos"
            }

            Text(
                text = mensaje,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    estado = EstadoPantalla.CARGANDO
                }
            ) {
                Text("Cargar")
            }

            Button(
                onClick = {
                    estado = EstadoPantalla.CONTENIDO
                }
            ) {
                Text("Éxito")
            }

            Button(
                onClick = {
                    estado = EstadoPantalla.ERROR
                }
            ) {
                Text("Error")
            }
        }

        Text(
            "Presiona los botones para observar los cambios"
        )
    }
}
