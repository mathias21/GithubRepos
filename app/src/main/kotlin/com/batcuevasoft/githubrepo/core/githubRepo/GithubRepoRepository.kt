package com.batcuevasoft.githubrepo.core.githubRepo

import com.batcuevasoft.githubrepo.data.local.githubRepo.GithubRepoLocalDatasource
import com.batcuevasoft.githubrepo.data.remote.githubRepo.GithubRepoRemoteDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
    }

    override fun getGithubRepoFlowById(repoId: Long): Flow<GithubRepo?> {
        return githubRepoEntityLocalDatasource.getGithubRepoEntityFlowById(repoId)
            .map { it?.toGithubRepo() }
    }

}