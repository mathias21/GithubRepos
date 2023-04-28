package com.batcuevasoft.githubrepo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.batcuevasoft.githubrepo.R
import com.batcuevasoft.githubrepo.ui.theme.GithubRepoTheme

@Composable
fun AuthorContent(
    avatarUrl: String? = null,
    authorName: String,
) {
    Row(
        Modifier
            .height(24.dp)
            .fillMaxWidth()) {
        Card(
            modifier = Modifier.size(24.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSurface),

            ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
                    .clip(CircleShape),
                model = avatarUrl ?: R.drawable.ic_default_avatar_placeholder,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }

        Box(
            Modifier
                .fillMaxHeight()
                .wrapContentWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = authorName,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorContentPreview() {
    GithubRepoTheme {
        AuthorContent(null, "name")
    }
}