package com.batcuevasoft.githubrepo.data.local.remoteKeys

import androidx.room.withTransaction
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepo
import com.batcuevasoft.githubrepo.data.local.GithubRepoDatabase
import com.batcuevasoft.githubrepo.data.local.githubRepo.GithubRepoEntity
import javax.inject.Inject

interface RemoteKeyLocalDatasource {
    suspend fun getAll(): List<RemoteKeyEntity>
    suspend fun updateKeysAndRepos(githubRepoList: List<GithubRepo>, isRefresh: Boolean, prevKey: Int?, nextKey: Int?)
    suspend fun getRemoteKeysByGithubRepoId(repoId: Long): RemoteKeyEntity?
}

class RemoteKeyLocalDatasourceImpl @Inject constructor(
    private val database: GithubRepoDatabase,
) : RemoteKeyLocalDatasource {

    private val keysDao = database.remoteKeyEntityDao()
    private val reposDao = database.githubRepoEntityDao()

    override suspend fun getAll(): List<RemoteKeyEntity> {
        return keysDao.getAll()
    }

    override suspend fun updateKeysAndRepos(githubRepoList: List<GithubRepo>, isRefresh: Boolean, prevKey: Int?, nextKey: Int?) {
        database.withTransaction {
            // clear all tables in the database
            if (isRefresh) {
                keysDao.clearRemoteKeys()
                reposDao.clearAll()
            }
            val keys = githubRepoList.map {
                RemoteKeyEntity(repoId = it.id, prevKey = prevKey, nextKey = nextKey)
            }
            keysDao.insertAll(keys)
            reposDao.insertAll(githubRepoList.map { it.toGithubRepoEntity() })
        }
    }

    override suspend fun getRemoteKeysByGithubRepoId(repoId: Long): RemoteKeyEntity? {
        return keysDao.getRemoteKeysByGithubRepoId(repoId)
    }
}