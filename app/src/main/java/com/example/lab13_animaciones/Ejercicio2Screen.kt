package com.example.lab13_animaciones

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Ejercicio2Screen() {

    var cambiarColor by remember {
        mutableStateOf(false)
    }

    var usarSpring by remember {
        mutableStateOf(false)
    }

    val colorAnimado by animateColorAsState(
        targetValue = if (cambiarColor) {
            Color.Green
        } else {
            Color.Blue
        },
        animationSpec = if (usarSpring) {
            spring()
        } else {
            tween(durationMillis = 1000)
        },
        label = "AnimacionColor"
    )

    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text("Ejercicio 2: Cambio de color")

        Box(
            modifier = Modifier
                .size(160.dp)
                .background(colorAnimado)
        )

        Button(
            onClick = {
                cambiarColor = !cambiarColor
            }
        ) {
            Text("Cambiar color")
        }

        Button(
            onClick = {
                usarSpring = !usarSpring
            }
        ) {
            Text(
                if (usarSpring) {
                    "Modo: Spring"
                } else {
                    "Modo: Tween"
                }
            )
        }

        Text("Experimenta con ambas animaciones")
    }
}
