package com.batcuevasoft.githubrepo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GithubRepoListViewModel @Inject constructor(
    private val githubRepoRepository: GithubRepoRepository,
) : ViewModel() {

    val pagingFlow = githubRepoRepository.githubRepoPagingSource
        .cachedIn(viewModelScope)

}