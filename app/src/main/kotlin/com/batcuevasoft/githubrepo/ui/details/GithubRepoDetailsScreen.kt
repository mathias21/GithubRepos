package com.batcuevasoft.githubrepo.ui.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.batcuevasoft.githubrepo.R
import com.batcuevasoft.githubrepo.core.extensions.formatToDateString
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepo
import com.batcuevasoft.githubrepo.core.githubRepo.RepoOwner
import com.batcuevasoft.githubrepo.ui.components.AuthorContent
import com.batcuevasoft.githubrepo.ui.components.GithubRepoChipList
import com.batcuevasoft.githubrepo.ui.components.LastUpdateAndFullNameContent
import com.batcuevasoft.githubrepo.ui.components.loading.LoadingComponent
import com.batcuevasoft.githubrepo.ui.components.screens.ErrorContent
import com.batcuevasoft.githubrepo.ui.components.screens.FullScreenBackgroundLayer
import com.batcuevasoft.githubrepo.ui.components.topbar.MainTopBar
import com.batcuevasoft.githubrepo.ui.components.topbar.MainTopBarConfiguration
import com.batcuevasoft.githubrepo.ui.components.topbar.TopBarIconConfig
import com.batcuevasoft.githubrepo.ui.details.GithubRepoDetailsViewModel.GithubRepoDetailsState.DataLoaded
import com.batcuevasoft.githubrepo.ui.details.GithubRepoDetailsViewModel.GithubRepoDetailsState.Error
import com.batcuevasoft.githubrepo.ui.details.GithubRepoDetailsViewModel.GithubRepoDetailsState.Loading
import com.batcuevasoft.githubrepo.ui.theme.GithubRepoTheme
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun GithubRepoDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubRepoDetailsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
) {

    val state by viewModel.githubRepoDetailsState.collectAsStateWithLifecycle()
    val uriHandler = LocalUriHandler.current

    BackHandler(enabled = true) {
        onBackPressed()
    }

    Scaffold(
        topBar = {
            Row(Modifier.padding(top = 36.dp)) {
                MainTopBar(
                    MainTopBarConfiguration(
                        leftIconConfig = TopBarIconConfig(
                            rememberVectorPainter(Icons.Filled.ArrowBack),
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = "",
                            onIconClick = onBackPressed
                        )
                    )
                )
            }
        }
    ) { paddingValues ->
        FullScreenBackgroundLayer()
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val currentState = state) {
                is Loading -> LoadingComponent()
                is DataLoaded -> GithubRepoDetailsContent(repo = currentState.githubRepo) {
                    uriHandler.openUri(it)
                }

                is Error -> ErrorContent(
                    title = stringResource(id = R.string.repo_details_error_title),
                    description = stringResource(id = R.string.repo_details_error_description),
                    buttonText = stringResource(id = R.string.ok_button),
                    onButtonPressed = onBackPressed
                )
            }
        }
    }
}

@Composable
private fun GithubRepoDetailsContent(
    modifier: Modifier = Modifier,
    repo: GithubRepo,
    onGoToRepositoryPressed: (String) -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        AuthorContent(
            avatarUrl = repo.owner.avatarUrl,
            authorName = repo.owner.name
        )
        Text(
            modifier = modifier,
            text = repo.name,
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = modifier,
            text = repo.language ?: stringResource(id = R.string.unknown_language),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        LastUpdateAndFullNameContent(
            updateDate = repo.lastUpdateDate.formatToDateString(),
            daysSinceUpdate = repo.daysSinceUpdate,
            fullName = repo.fullName
        )
        Spacer(modifier = Modifier.height(8.dp))
        GithubRepoChipList(
            starCount = repo.starCount,
            forkCount = repo.forkCount
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = modifier,
            text = repo.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Justify
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onGoToRepositoryPressed(repo.repoUrl) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 32.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.go_to_repository_url_button)
                )
            }
        }
    }
}


@Preview(backgroundColor = 0xFF0BA6EC, showBackground = true)
@Composable
private fun GithubRepoDetailsContentPreview(
    description: String = LoremIpsum(30).values.joinToString(" "),
) {
    GithubRepoTheme {
        FullScreenBackgroundLayer()
        GithubRepoDetailsContent(
            repo = GithubRepo(
                id = 12,
                name = "My lovely Repo",
                fullName = "repos/fullname",
                description = description,
                starCount = 160,
                lastUpdateDate = Date(),
                daysSinceUpdate = 23,
                repoUrl = "mathias21/ReusableRecycler",
                forkCount = 12,
                RepoOwner(
                    "mathias",
                    null
                ),
                language = "Kotlin"
            )
        ) {

        }
    }
}