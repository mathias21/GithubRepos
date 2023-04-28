package com.batcuevasoft.githubrepo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.batcuevasoft.githubrepo.ui.theme.GithubRepoTheme

@Composable
fun StarCountContent(
    modifier: Modifier = Modifier,
    startCount: Int,
) {
    Row(
        modifier
            .wrapContentWidth()
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            Icons.Filled.Star,
            modifier = Modifier.height(32.dp),
            tint = Color.Yellow,
            contentDescription = "icon"
        )

        Spacer(modifier = Modifier.width(4.dp))

        Box(Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
            Text(
                text = startCount.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0x3A9CD8
)
@Composable
fun StarCountContentPreview() {
    GithubRepoTheme {
        StarCountContent(startCount = 158)
    }
}