package com.batcuevasoft.githubrepo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.batcuevasoft.githubrepo.ui.theme.GithubRepoTheme

@Composable
fun LastUpdateAndFullNameContent(
    updateDate: String,
    daysSinceUpdate: Int,
    fullName: String,
) {

    Row(
        Modifier
            .height(24.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(8.dp)
                .background(
                    color = when (daysSinceUpdate) {
                        in 0..10 -> Color.Green
                        in 11..30 -> Color.Yellow
                        else -> Color.Red
                    },
                    shape = CircleShape
                )
        )

        Box(
            Modifier
                .fillMaxHeight()
                .wrapContentWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = updateDate,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .size(4.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            Modifier
                .fillMaxHeight()
                .wrapContentWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = fullName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LastUpdateAndUrlContentPreview() {
    GithubRepoTheme {
        LastUpdateAndFullNameContent(
            "January 1, 2021",
            20,
            "mathias21/ReusableRecycler"
        )
    }
}