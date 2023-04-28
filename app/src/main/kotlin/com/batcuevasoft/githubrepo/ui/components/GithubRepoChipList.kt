package com.batcuevasoft.githubrepo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.batcuevasoft.githubrepo.R
import com.batcuevasoft.githubrepo.ui.theme.GithubRepoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubRepoChipList(
    modifier: Modifier = Modifier,
    starCount: Int,
    forkCount: Int,
) {

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            AssistChip(
                trailingIcon = {
                    Icon(
                        Icons.Filled.Star,
                        modifier = Modifier.height(16.dp),
                        tint = Color.Yellow,
                        contentDescription = "icon"
                    )
                },
                onClick = {

                },
                label = {
                    Text(
                        text = starCount.toString(),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
        }

        item {
            AssistChip(
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.fork_icon),
                        modifier = Modifier.height(16.dp)
                            .padding(2.dp),
                        contentDescription = "fork"
                    )
                },
                onClick = {

                },
                label = {
                    Text(
                        text = forkCount.toString(),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GithubRepoChipListPreview() {
    GithubRepoTheme {
        GithubRepoChipList(
            starCount = 123,
            forkCount = 123
        )
    }
}