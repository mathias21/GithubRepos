package com.batcuevasoft.githubrepo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepo
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepoRepository
import com.batcuevasoft.githubrepo.core.util.DispatcherProvider
import com.batcuevasoft.githubrepo.core.util.stateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class GithubRepoListViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val githubRepoRepository: GithubRepoRepository,
) : ViewModel() {

    val githubRepoDetailsState: StateFlow<GithubRepoListState> = githubRepoRepository.githubRepoListFlow.map {
        GithubRepoListState.DataLoaded(it.sortedByDescending { it.starCount })
    }.flowOn(dispatcherProvider.io)
        .stateIn(viewModelScope, GithubRepoListState.Loading)

    sealed interface GithubRepoListState {
        object Loading : GithubRepoListState
        data class DataLoaded(val githubRepoList: List<GithubRepo>) : GithubRepoListState
    }
}