package com.batcuevasoft.githubrepo.data.local.remoteKeys

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeyEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeyEntity>)

    @Query("SELECT * FROM $REMOTE_KEY_ENTITY_TABLE WHERE repoId = :repoId")
    suspend fun getRemoteKeysByGithubRepoId(repoId: Long): RemoteKeyEntity?

    @Query("SELECT * FROM $REMOTE_KEY_ENTITY_TABLE")
    suspend fun getAll(): List<RemoteKeyEntity>

    @Query("DELETE FROM $REMOTE_KEY_ENTITY_TABLE")
    suspend fun clearRemoteKeys()
}