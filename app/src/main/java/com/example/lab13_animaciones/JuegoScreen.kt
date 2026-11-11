package com.example.lab13_animaciones

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun JuegoScreen() {

    var jugando by remember {
        mutableStateOf(false)
    }

    var posicionJugador by remember {
        mutableIntStateOf(0)
    }

    var posicionEstrella by remember {
        mutableIntStateOf(2)
    }

    var puntos by remember {
        mutableIntStateOf(0)
    }

    var estrellaVisible by remember {
        mutableStateOf(true)
    }

    var personajeGrande by remember {
        mutableStateOf(false)
    }

    val gano = puntos >= 3

    // Movimiento horizontal del personaje
    val desplazamiento by animateDpAsState(
        targetValue = (posicionJugador * 90).dp,
        animationSpec = tween(400),
        label = "MovimientoJugador"
    )

    // Cambio de tamaño del personaje
    val tamanoPersonaje by animateDpAsState(
        targetValue = if (personajeGrande) 52.dp else 40.dp,
        animationSpec = tween(400),
        label = "TamanoJugador"
    )

    // Cambio de color del personaje
    val colorPersonaje by animateColorAsState(
        targetValue = if (personajeGrande) {
            Color.Yellow
        } else {
            Color.Green
        },
        animationSpec = tween(400),
        label = "ColorJugador"
    )

    // Animacion al recoger una estrella
    LaunchedEffect(puntos) {
        if (puntos in 1..2) {
            estrellaVisible = false
            delay(400)
            posicionEstrella = (posicionEstrella + 2) % 3
            estrellaVisible = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Atrapa la estrella",
            style = MaterialTheme.typography.headlineSmall
        )

        // Puntaje animado
        AnimatedContent(
            targetState = puntos,
            transitionSpec = {
                fadeIn(tween(300)) togetherWith
                        fadeOut(tween(300))
            },
            label = "Puntaje"
        ) { puntaje ->
            Text(
                text = "Puntos: $puntaje / 3",
                style = MaterialTheme.typography.titleLarge
            )
        }

        // Escenario
        Box(
            modifier = Modifier
                .width(280.dp)
                .height(180.dp)
                .background(Color(0xFF10233F))
        ) {

            if (jugando && !gano) {

                // Estrella animada

                Box(
                    modifier = Modifier.offset(
                        x = (posicionEstrella * 90).dp,
                        y = 25.dp
                    )
                ) {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = estrellaVisible,
                        enter = fadeIn(
                            animationSpec = tween(durationMillis = 300)
                        ),
                        exit = fadeOut(
                            animationSpec = tween(durationMillis = 300)
                        )
                    ) {
                        Text(
                            text = "⭐",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                }
                // Personaje animado
                Box(
                    modifier = Modifier
                        .offset(
                            x = desplazamiento,
                            y = 110.dp
                        )
                        .size(tamanoPersonaje)
                        .background(colorPersonaje),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "P",
                        color = Color.Black
                    )
                }
            }
        }

        // Mensajes animados
        AnimatedContent(
            targetState = when {
                gano -> "¡Ganaste!"
                !jugando -> "Presiona iniciar"
                posicionJugador == posicionEstrella ->
                    "¡Recoge la estrella!"
                else -> "Muévete hasta la estrella"
            },
            transitionSpec = {
                fadeIn(tween(300)) togetherWith
                        fadeOut(tween(300))
            },
            label = "MensajeJuego"
        ) { mensaje ->
            Text(mensaje)
        }

        if (!jugando || gano) {

            Button(
                onClick = {
                    puntos = 0
                    posicionJugador = 0
                    posicionEstrella = 2
                    personajeGrande = false
                    estrellaVisible = true
                    jugando = true
                }
            ) {
                Text(
                    if (gano) "Volver a jugar"
                    else "Iniciar juego"
                )
            }

        } else {

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Button(
                    enabled = posicionJugador > 0,
                    onClick = {
                        posicionJugador--
                    }
                ) {
                    Text("←")
                }

                Button(
                    enabled = estrellaVisible &&
                            posicionJugador == posicionEstrella,
                    onClick = {
                        puntos++
                        personajeGrande = !personajeGrande
                    }
                ) {
                    Text("Recoger")
                }

                Button(
                    enabled = posicionJugador < 2,
                    onClick = {
                        posicionJugador++
                    }
                ) {
                    Text("→")
                }
            }
        }

        Text(
            "Mueve el personaje y recoge 3 estrellas"
        )
    }
}
