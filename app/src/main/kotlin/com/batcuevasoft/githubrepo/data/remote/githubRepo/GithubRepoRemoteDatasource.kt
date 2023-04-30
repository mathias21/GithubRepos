package com.batcuevasoft.githubrepo.data.remote.githubRepo

import com.batcuevasoft.githubrepo.data.remote.NetworkResponse
import com.batcuevasoft.githubrepo.data.remote.tryNetworkSuspending
import javax.inject.Inject

interface GithubRepoRemoteDatasource {
    suspend fun fetchRepositories(): NetworkResponse<List<GithubRepoRemoteEntity>>
    suspend fun fetchRepositories(query: String, perPage: Int, page: Int): NetworkResponse<GithubRepoPageResponse>
}

class GithubRepoRemoteDatasourceImpl @Inject constructor(
    private val githubApi: GithubApi
) : GithubRepoRemoteDatasource {

    override suspend fun fetchRepositories(): NetworkResponse<List<GithubRepoRemoteEntity>> {
        return tryNetworkSuspending {
            githubApi.getUserRepositories(USERNAME)
        }
    }

    override suspend fun fetchRepositories(query: String, perPage: Int, page: Int): NetworkResponse<GithubRepoPageResponse> {
        return tryNetworkSuspending {
            githubApi.getRepositories(query, perPageCount = perPage, page = page)
        }
    }

    companion object {
        private const val USERNAME = "mathias21"
    }
}