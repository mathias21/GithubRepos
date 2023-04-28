package com.batcuevasoft.githubrepo.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.batcuevasoft.githubrepo.R
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepo
import com.batcuevasoft.githubrepo.core.githubRepo.RepoOwner
import com.batcuevasoft.githubrepo.ui.components.list.GithubRepoRowContent
import com.batcuevasoft.githubrepo.ui.components.loading.LoadingComponent
import com.batcuevasoft.githubrepo.ui.main.GithubRepoListViewModel.GithubRepoListState.DataLoaded
import com.batcuevasoft.githubrepo.ui.main.GithubRepoListViewModel.GithubRepoListState.Loading
import com.batcuevasoft.githubrepo.ui.theme.GithubRepoTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun GithubRepoListScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubRepoListViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
) {

    val state by viewModel.githubRepoDetailsState.collectAsStateWithLifecycle()

    BackHandler(enabled = true) {
        onBackPressed()
    }

    Scaffold { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.repo_list_title),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            when (val currentState = state) {
                is Loading -> LoadingComponent()
                is DataLoaded -> GithubRepoListContent(repoList = currentState.githubRepoList) {

                }
            }
        }
    }
}

@Composable
private fun GithubRepoListContent(
    modifier: Modifier = Modifier,
    repoList: List<GithubRepo>,
    onRepoClick: (Long) -> Unit
) {
    Crossfade(repoList) { repoList ->
        LazyColumn(
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(repoList, key = { it.id } ) {
                GithubRepoRowContent(
                    githubRepo = it
                ) {
                    onRepoClick(it.id)
                }
            }
        }
    }
}


@Preview
@Composable
private fun GithubRepoListContentPreview(
    description: String = LoremIpsum(10).values.joinToString(" "),
) {
    GithubRepoTheme() {
        GithubRepoListContent(
            repoList = listOf(
                GithubRepo(
                    id = 10,
                    name = "Repo name 1",
                    description = description,
                    starCount = 20,
                    lastUpdateDate = Date(),
                    daysSinceUpdate = 9,
                    repoUrl = "mathias21/ReusableRecycler",
                    forkCount = 12,
                    owner = RepoOwner(
                        "mathias",
                        null
                    )
                ),
                GithubRepo(
                    id = 11,
                    name = "Repo name 2",
                    description = description,
                    starCount = 10,
                    lastUpdateDate = Date(),
                    daysSinceUpdate = 12,
                    repoUrl = "mathias21/ReusableRecycler",
                    forkCount = 12,
                    owner = RepoOwner(
                        "mathias",
                        null
                    )
                ),
                GithubRepo(
                    id = 13,
                    name = "Repo name 3",
                    description = description,
                    starCount = 30,
                    lastUpdateDate = Date(),
                    daysSinceUpdate = 3,
                    repoUrl = "mathias21/ReusableRecycler",
                    forkCount = 12,
                    owner = RepoOwner(
                        "mathias",
                        null
                    )
                )
            )
        ) {

        }
    }
}