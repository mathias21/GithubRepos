package com.batcuevasoft.githubrepo.core.githubRepo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.batcuevasoft.githubrepo.data.local.githubRepo.GithubRepoLocalDatasource
import com.batcuevasoft.githubrepo.data.local.remoteKeys.RemoteKeyLocalDatasource
import com.batcuevasoft.githubrepo.data.remote.NetworkResponse
import com.batcuevasoft.githubrepo.data.remote.githubRepo.GithubRepoRemoteDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


interface GithubRepoRepository {
    val githubRepoPagingSource: Flow<PagingData<GithubRepo>>
    val githubRepoListFlow: Flow<List<GithubRepo>>
    fun getGithubRepoFlowById(repoId: Long): Flow<GithubRepo?>
}

@OptIn(ExperimentalPagingApi::class)
class GithubRepoRepositoryImpl @Inject constructor(
    private val githubRepoEntityLocalDatasource: GithubRepoLocalDatasource,
    private val githubRepoRemoteDatasource: GithubRepoRemoteDatasource,
    private val remoteKeyLocalDatasource: RemoteKeyLocalDatasource
) : GithubRepoRepository {

    override val githubRepoPagingSource: Flow<PagingData<GithubRepo>> = Pager(
            config = PagingConfig(pageSize = 8),
            remoteMediator = GithubRemoteMediator(
                query = "Android",
                githubRepoRemoteDatasource,
                remoteKeyLocalDatasource
            ),
            pagingSourceFactory = { githubRepoEntityLocalDatasource.getPagingSource() }
        ).flow.map {
            it.map { it.toGithubRepo() }
    }

    override val githubRepoListFlow: Flow<List<GithubRepo>> = githubRepoEntityLocalDatasource.flow.map {
            it.map { it.toGithubRepo() }
        }
        .onStart {
            val networkResponse = githubRepoRemoteDatasource.fetchRepositories()
            when (networkResponse) {
                is NetworkResponse.Success -> {
                    networkResponse.data.forEach {
                        githubRepoEntityLocalDatasource.insertGithubRepoEntity(
                            id = it.id,
                            name = it.name,
                            fullName = it.fullName,
                            description = it.description.orEmpty(),
                            stars = it.starCount,
                            repoUrl = it.url,
                            forkCount = it.forkCount,
                            lastUpdateTime = it.updatedAt.time,
                            authorName = it.owner.username,
                            avatarUrl = it.owner.avatarUrl,
                            language = it.language
                        )
                    }
                }

                else -> Unit // TODO HANDLE ERRORS IF NEEDED
            }
        }

    override fun getGithubRepoFlowById(repoId: Long): Flow<GithubRepo?> {
        return githubRepoEntityLocalDatasource.getGithubRepoEntityFlowById(repoId)
            .map { it?.toGithubRepo() }
    }
}