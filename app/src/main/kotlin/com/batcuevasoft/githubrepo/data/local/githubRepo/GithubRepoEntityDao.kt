package com.batcuevasoft.githubrepo.data.local.githubRepo

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubRepoEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubRepoEntity(entry: GithubRepoEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<GithubRepoEntity>)

    @Query("SELECT * FROM $GITHUB_REPO_ENTITY_TABLE order by stars desc")
    fun pagingSource(): PagingSource<Int, GithubRepoEntity>

    @Query("Select * from $GITHUB_REPO_ENTITY_TABLE")
    suspend fun all(): List<GithubRepoEntity>

    @Query("Select * from $GITHUB_REPO_ENTITY_TABLE")
    fun allFlow(): Flow<List<GithubRepoEntity>>

    @Query("Select * from $GITHUB_REPO_ENTITY_TABLE where id = :id")
    fun getGithubRepoEntityById(id: Long): GithubRepoEntity?

    @Query("Select * from $GITHUB_REPO_ENTITY_TABLE where id = :id")
    fun getGithubRepoEntityFlowById(id: Long): Flow<GithubRepoEntity?>

    @Update
    fun updateGithubRepoEntity(githubRepoEntity: GithubRepoEntity)

    @Delete
    fun removeGithubRepoEntity(githubRepoEntity: GithubRepoEntity)

    @Query("DELETE FROM $GITHUB_REPO_ENTITY_TABLE")
    suspend fun clearAll()
}