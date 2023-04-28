package com.batcuevasoft.githubrepo.data.local.githubRepo

import com.batcuevasoft.githubrepo.data.local.GithubRepoDatabase
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

interface GithubRepoLocalDatasource {
    val flow: Flow<List<GithubRepoEntity>>

    suspend fun insertGithubRepoEntity(
        name: String,
        description: String,
        stars: Int,
        repoUrl: String,
        forkCount: Int,
        authorName: String,
        avatarUrl: String? = null
    ): GithubRepoEntity

    suspend fun getGithubRepoEntityById(id: Long): GithubRepoEntity?
    fun getGithubRepoEntityFlowById(id: Long): Flow<GithubRepoEntity?>
    suspend fun removeGithubRepoEntityById(id: Long)
}

class GithubRepoLocalDatasourceImpl @Inject constructor(
    private val database: GithubRepoDatabase,
) : GithubRepoLocalDatasource {

    private val dao = database.githubRepoEntityDao()
    override val flow: Flow<List<GithubRepoEntity>> = dao.allFlow()

    override suspend fun insertGithubRepoEntity(
        name: String,
        description: String,
        stars: Int,
        repoUrl: String,
        forkCount: Int,
        authorName: String,
        avatarUrl: String?,
    ): GithubRepoEntity {
        val entity = GithubRepoEntity(
            name = name,
            description = description,
            stars = stars,
            lastUpdateTimestamp = Date().time,
            repoUrl = repoUrl,
            forkCount = forkCount,
            authorName = authorName,
            avatarUrl = avatarUrl
        )
        val id = dao.insertGithubRepoEntity(entity)
        return entity.copy(id = id)
    }

    override suspend fun getGithubRepoEntityById(id: Long): GithubRepoEntity? {
        return dao.getGithubRepoEntityById(id)
    }

    override fun getGithubRepoEntityFlowById(id: Long): Flow<GithubRepoEntity?> {
        return dao.getGithubRepoEntityFlowById(id)
    }

    override suspend fun removeGithubRepoEntityById(id: Long) {
        dao.getGithubRepoEntityById(id)?.let {
            dao.removeGithubRepoEntity(it)
        }
    }

}