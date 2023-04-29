package com.batcuevasoft.githubrepo.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.batcuevasoft.githubrepo.R
import com.batcuevasoft.githubrepo.ui.components.list.GithubRepoRowContent
import com.batcuevasoft.githubrepo.ui.components.loading.LoadingComponent
import com.batcuevasoft.githubrepo.ui.components.screens.FullScreenBackgroundLayer
import com.batcuevasoft.githubrepo.ui.main.GithubRepoListViewModel.GithubRepoListState.DataLoaded
import com.batcuevasoft.githubrepo.ui.main.GithubRepoListViewModel.GithubRepoListState.Loading

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun GithubRepoListScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubRepoListViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onRepoClick: (Long) -> Unit,
) {

    val state by viewModel.githubRepoDetailsState.collectAsStateWithLifecycle()

    BackHandler(enabled = true) {
        onBackPressed()
    }

    Scaffold { paddingValues ->
        FullScreenBackgroundLayer()
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(24.dp + paddingValues.calculateTopPadding()))
                Text(
                    modifier = modifier,
                    text = stringResource(id = R.string.repo_list_title),
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
            when (val currentState = state) {
                is Loading -> item {
                    LoadingComponent()
                }

                is DataLoaded -> {
                    items(currentState.githubRepoList, key = { it.id }) {
                        GithubRepoRowContent(
                            githubRepo = it
                        ) {
                            onRepoClick(it.id)
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(32.dp + paddingValues.calculateBottomPadding()))
                    }
                }
            }
        }
    }
}