package com.example.lab13_animaciones

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Ejercicio3Screen() {

    var expandido by remember {
        mutableStateOf(false)
    }

    // Animacion del tamaño
    val tamano by animateDpAsState(
        targetValue = if (expandido) 100.dp else 60.dp,
        animationSpec = tween(durationMillis = 800),
        label = "Tamano"
    )

    // Animacion de posicion horizontal
    val posicionX by animateDpAsState(
        targetValue = if (expandido) 140.dp else 0.dp,
        animationSpec = tween(durationMillis = 800),
        label = "PosicionX"
    )

    // Animacion de posicion vertical
    val posicionY by animateDpAsState(
        targetValue = if (expandido) 60.dp else 0.dp,
        animationSpec = tween(durationMillis = 800),
        label = "PosicionY"
    )

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text("Ejercicio 3: Tamaño y posición")

        Box(
            modifier = Modifier
                .width(280.dp)
                .height(200.dp)
                .background(Color.LightGray)
        ) {

            Box(
                modifier = Modifier
                    .offset(
                        x = posicionX,
                        y = posicionY
                    )
                    .size(tamano)
                    .background(Color.Magenta)
            )
        }

        Button(
            onClick = {
                expandido = !expandido
            }
        ) {
            Text(
                if (expandido) {
                    "Restablecer"
                } else {
                    "Mover y ampliar"
                }
            )
        }

        Text(
            "Observa el movimiento y tamaño del cuadro"
        )
    }
}
