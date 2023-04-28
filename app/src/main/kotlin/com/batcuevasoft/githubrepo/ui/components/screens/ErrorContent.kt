package com.batcuevasoft.githubrepo.ui.components.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.batcuevasoft.githubrepo.R


@Composable
fun ErrorContent(
    title: String = stringResource(id = R.string.default_error_title),
    description: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    onButtonPressed: () -> Unit,
) {
    TitleSubtitleButtonContent(
        title = title,
        subtitle = description,
        buttonText = buttonText,
        modifier = modifier,
        onButtonPressed = onButtonPressed
    )
}