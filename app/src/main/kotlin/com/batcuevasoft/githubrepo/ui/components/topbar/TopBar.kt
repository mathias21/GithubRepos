package com.batcuevasoft.githubrepo.ui.components.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun MainTopBar(
    config: MainTopBarConfiguration,
    modifier: Modifier = Modifier,
) {

    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
        modifier = modifier
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (config.leftIconConfig.isVisible) {
                IconButton(onClick = config.leftIconConfig.onIconClick) {
                    Icon(
                        config.leftIconConfig.painter,
                        tint = config.leftIconConfig.tint,
                        contentDescription = config.leftIconConfig.contentDescription
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(1.dp))
            }
        }
    }
}

data class MainTopBarConfiguration(
    val leftIconConfig: TopBarIconConfig,
    val backgroundColor: Color? = null,
)

data class TopBarIconConfig(
    val painter: Painter,
    val tint: Color,
    val contentDescription: String,
    val isVisible: Boolean = true,
    val isAnimated: Boolean = false,
    val onIconClick: () -> Unit,
)