package com.batcuevasoft.githubrepo.ui.components.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.batcuevasoft.githubrepo.core.extensions.formatToDateString
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepo
import com.batcuevasoft.githubrepo.core.githubRepo.RepoOwner
import com.batcuevasoft.githubrepo.ui.components.AuthorContent
import com.batcuevasoft.githubrepo.ui.components.GithubRepoChipList
import com.batcuevasoft.githubrepo.ui.components.LastUpdateAndFullNameContent
import com.batcuevasoft.githubrepo.ui.theme.GithubRepoTheme
import java.util.Date


@Composable
fun GithubRepoRowContent(
    modifier: Modifier = Modifier,
    githubRepo: GithubRepo,
    onClick: () -> Unit,
) {

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 180.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            AuthorContent(
                avatarUrl = githubRepo.owner.avatarUrl,
                authorName = githubRepo.owner.name
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = githubRepo.name,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            LastUpdateAndFullNameContent(
                updateDate = githubRepo.lastUpdateDate.formatToDateString(),
                daysSinceUpdate = githubRepo.daysSinceUpdate,
                fullName = githubRepo.fullName
            )

            Spacer(modifier = Modifier.height(12.dp))

            GithubRepoChipList(
                starCount = githubRepo.starCount,
                forkCount = githubRepo.forkCount
            )
        }
    }
}

@Preview
@Composable
private fun GithubRepoRowContentPreview(
    description: String = LoremIpsum(10).values.joinToString(" "),
) {
    GithubRepoTheme {
        GithubRepoRowContent(
            githubRepo = GithubRepo(
                id = 12,
                name = "My lovely Repo",
                fullName = "repos/fullname",
                description = description,
                starCount = 160,
                lastUpdateDate = Date(),
                daysSinceUpdate = 1,
                repoUrl = "mathias21/ReusableRecycler",
                forkCount = 12,
                RepoOwner(
                    "mathias",
                    null
                ),
                language = "Kotlin"
            ),
            onClick = {}
        )
    }
}
