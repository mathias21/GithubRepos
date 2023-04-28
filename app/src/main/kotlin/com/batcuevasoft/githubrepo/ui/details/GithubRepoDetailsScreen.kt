package com.batcuevasoft.githubrepo.ui.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.batcuevasoft.githubrepo.R
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepo
import com.batcuevasoft.githubrepo.core.githubRepo.RepoOwner
import com.batcuevasoft.githubrepo.ui.components.StarCountContent
import com.batcuevasoft.githubrepo.ui.components.loading.LoadingComponent
import com.batcuevasoft.githubrepo.ui.components.screens.ErrorContent
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
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val currentState = state) {
                is Loading -> LoadingComponent()
                is DataLoaded -> GithubRepoDetailsContent(details = currentState.githubRepo)
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
    details: GithubRepo
) {
    Column(
        Modifier.fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = modifier,
            text = details.name,
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        StarCountContent(startCount = details.starCount)
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = modifier,
            text = details.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Justify
        )
    }
}


@Preview
@Composable
private fun GithubRepoDetailsContentPreview(
    description: String = LoremIpsum(30).values.joinToString(" ")
) {
    GithubRepoTheme() {
        GithubRepoDetailsContent(
            details = GithubRepo(
                id = 12,
                name = "My lovely Repo",
                description = description,
                starCount = 160,
                lastUpdateDate = Date(),
                daysSinceUpdate = 23,
                repoUrl = "mathias21/ReusableRecycler",
                forkCount = 12,
                RepoOwner(
                    "mathias",
                    null
                )
            )
        )
    }
}