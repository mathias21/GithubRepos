package com.batcuevasoft.githubrepo.data.local.remoteKeys

import androidx.room.Entity
import androidx.room.PrimaryKey

const val REMOTE_KEY_ENTITY_TABLE = "RemoteKeyTable"

@Entity(tableName = REMOTE_KEY_ENTITY_TABLE)
data class RemoteKeyEntity(
    @PrimaryKey val repoId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)