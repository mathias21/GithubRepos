package com.batcuevasoft.githubrepo.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

@Composable
fun FullScreenBackgroundLayer(
    modifier: Modifier = Modifier,
    backgroundStops: Array<Pair<Float, Color>> = backgroundColorStops()
) {
    Box(Modifier.fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colorStops = backgroundStops,
                        tileMode = TileMode.Clamp
                    )
                )
        )
    }
}

@Composable
fun backgroundColorStops(): Array<Pair<Float, Color>> {
    return arrayOf(
        0.2f to MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15f),
        0.9f to MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)
    )
}