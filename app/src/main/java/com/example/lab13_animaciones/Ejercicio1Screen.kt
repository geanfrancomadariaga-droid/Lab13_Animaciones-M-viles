package com.example.lab13_animaciones
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
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
fun Ejercicio1Screen() {
    var visible by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Ejercicio 1: AnimatedVisibility")

        Button(
            onClick = { visible = !visible }
        ) {
            Text(
                if (visible) "Ocultar cuadro"
                else "Mostrar cuadro"
            )
        }

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(
                animationSpec = tween(1000)
            ),
            exit = fadeOut(
                animationSpec = tween(1000)
            )
        ) {
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .background(Color.Blue)
            )
        }

        Text("Animación de entrada y salida")
    }
}
