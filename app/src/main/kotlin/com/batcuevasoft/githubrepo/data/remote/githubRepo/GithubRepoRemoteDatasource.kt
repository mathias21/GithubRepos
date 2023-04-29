package com.batcuevasoft.githubrepo.data.remote.githubRepo

import com.batcuevasoft.githubrepo.data.remote.NetworkResponse
import com.batcuevasoft.githubrepo.data.remote.tryNetworkSuspending
import javax.inject.Inject

interface GithubRepoRemoteDatasource {
    suspend fun fetchRepositories(): NetworkResponse<List<GithubRepoRemoteEntity>>
}

class GithubRepoRemoteDatasourceImpl @Inject constructor(
    private val githubApi: GithubApi
) : GithubRepoRemoteDatasource {

    override suspend fun fetchRepositories(): NetworkResponse<List<GithubRepoRemoteEntity>> {
        return tryNetworkSuspending {
            githubApi.getUserRepositories(USERNAME)
        }
    }

    companion object {
        private const val USERNAME = "mathias21"
    }
}