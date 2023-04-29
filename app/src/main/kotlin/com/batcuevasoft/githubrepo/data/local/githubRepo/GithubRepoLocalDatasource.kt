package com.batcuevasoft.githubrepo.data.local.githubRepo

import com.batcuevasoft.githubrepo.data.local.GithubRepoDatabase
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

interface GithubRepoLocalDatasource {
    val flow: Flow<List<GithubRepoEntity>>

    suspend fun insertGithubRepoEntity(
        id: Long = 0L,
        name: String,
        fullName: String,
        description: String,
        stars: Int,
        repoUrl: String,
        forkCount: Int,
        lastUpdateTime: Long,
        authorName: String,
        avatarUrl: String? = null,
        language: String? = null
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
        id: Long,
        name: String,
        fullName: String,
        description: String,
        stars: Int,
        repoUrl: String,
        forkCount: Int,
        lastUpdateTime: Long,
        authorName: String,
        avatarUrl: String?,
        language: String?
    ): GithubRepoEntity {
        val entity = GithubRepoEntity(
            id = id,
            name = name,
            fullName = fullName,
            description = description,
            stars = stars,
            lastUpdateTimestamp = lastUpdateTime,
            repoUrl = repoUrl,
            forkCount = forkCount,
            authorName = authorName,
            avatarUrl = avatarUrl,
            language = language
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