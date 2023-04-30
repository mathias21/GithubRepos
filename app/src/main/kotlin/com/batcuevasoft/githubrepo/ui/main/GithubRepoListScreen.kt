package com.batcuevasoft.githubrepo.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.batcuevasoft.githubrepo.R
import com.batcuevasoft.githubrepo.ui.components.list.GithubRepoRowContent
import com.batcuevasoft.githubrepo.ui.components.loading.LoadingComponent
import com.batcuevasoft.githubrepo.ui.components.screens.FullScreenBackgroundLayer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubRepoListScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubRepoListViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onRepoClick: (Long) -> Unit,
) {

    val pagingFlow = viewModel.pagingFlow.collectAsLazyPagingItems()
    var isAppendLoading by remember { mutableStateOf(false) }
    var isAppendError by remember { mutableStateOf(false) }

    BackHandler(enabled = true) {
        onBackPressed()
    }

    Scaffold { paddingValues ->
        FullScreenBackgroundLayer()
        with(pagingFlow.loadState) {
            isAppendLoading = append is LoadState.Loading
            isAppendError = append is LoadState.Error
            if (refresh is LoadState.Loading) {
                LoadingComponent()
            }
        }
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            state = pagingFlow.rememberLazyListUpdatedState()
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
            items(items = pagingFlow, key = { it.id }) {
                it?.let {
                    GithubRepoRowContent(
                        githubRepo = it
                    ) {
                        onRepoClick(it.id)
                    }
                }
            }
            if (isAppendLoading) {
                item {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                    ) {
                        LoadingComponent()
                    }
                }
            }
            if (isAppendError) {
                item {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(stringResource(id = R.string.github_loading_error))
                    }
                }
            }
            item{
                Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 32.dp))
            }
        }
    }
}

@Composable
fun <T : Any> LazyPagingItems<T>.rememberLazyListUpdatedState(): LazyListState {
    // After recreation, LazyPagingItems first return 0 items, then the cached items.
    // This behavior/issue is resetting the LazyListState scroll position.
    // Below is a workaround. More info: https://issuetracker.google.com/issues/177245496.
    return when (itemCount) {
        // Return a different LazyListState instance.
        0 -> remember(this) { LazyListState(0, 0) }
        // Return rememberLazyListState (normal case).
        else -> androidx.compose.foundation.lazy.rememberLazyListState()
    }
}