package com.batcuevasoft.githubrepo.ui.components.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TitleSubtitleButtonContent(
    title: String,
    subtitle: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    isButtonVisible: Boolean = true,
    isButtonEnabled: Boolean = true,
    onButtonPressed: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(32.dp),
            text = title,
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            modifier = Modifier.padding(horizontal = 32.dp),
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                enabled = isButtonEnabled,
                onClick = onButtonPressed,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 32.dp),
            ) {
                Text(
                    text = buttonText
                )
            }
        }
    }
}