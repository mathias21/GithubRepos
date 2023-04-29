package com.batcuevasoft.githubrepo.core.githubRepo

import com.batcuevasoft.githubrepo.data.local.githubRepo.GithubRepoLocalDatasource
import com.batcuevasoft.githubrepo.data.remote.NetworkResponse
import com.batcuevasoft.githubrepo.data.remote.githubRepo.GithubRepoRemoteDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.util.Date
import javax.inject.Inject


interface GithubRepoRepository {
    val githubRepoListFlow: Flow<List<GithubRepo>>
    fun getGithubRepoFlowById(repoId: Long): Flow<GithubRepo?>
}

class GithubRepoRepositoryImpl @Inject constructor(
    private val githubRepoEntityLocalDatasource: GithubRepoLocalDatasource,
    private val githubRepoRemoteDatasource: GithubRepoRemoteDatasource
) : GithubRepoRepository {

    override val githubRepoListFlow: Flow<List<GithubRepo>> = githubRepoEntityLocalDatasource.flow.map {
        it.map { it.toGithubRepo() }
    }.onStart {
        val networkResponse = githubRepoRemoteDatasource.fetchRepositories()
        when(networkResponse) {
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
            else -> Unit
        }
    }

    override fun getGithubRepoFlowById(repoId: Long): Flow<GithubRepo?> {
        return githubRepoEntityLocalDatasource.getGithubRepoEntityFlowById(repoId)
            .map { it?.toGithubRepo() }
    }

}