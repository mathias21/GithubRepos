package com.batcuevasoft.githubrepo.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepo
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepoRepository
import com.batcuevasoft.githubrepo.core.util.DispatcherProvider
import com.batcuevasoft.githubrepo.core.util.stateIn
import com.batcuevasoft.githubrepo.ui.MainNavigation.REPO_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class GithubRepoDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dispatcherProvider: DispatcherProvider,
    private val githubRepoRepository: GithubRepoRepository,
) : ViewModel() {

    private val repoId: Long = checkNotNull(savedStateHandle[REPO_ID_ARG])

    val githubRepoDetailsState: StateFlow<GithubRepoDetailsState> = githubRepoRepository.getGithubRepoFlowById(repoId).map { repo ->
        repo?.let {
            GithubRepoDetailsState.DataLoaded(it)
        } ?: GithubRepoDetailsState.Error
    }.stateIn(viewModelScope, GithubRepoDetailsState.Loading)

    sealed interface GithubRepoDetailsState {
        object Loading : GithubRepoDetailsState
        data class DataLoaded(val githubRepo: GithubRepo) : GithubRepoDetailsState
        object Error : GithubRepoDetailsState
    }
}