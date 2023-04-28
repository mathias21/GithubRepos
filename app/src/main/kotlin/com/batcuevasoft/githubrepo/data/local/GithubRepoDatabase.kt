package com.batcuevasoft.githubrepo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.batcuevasoft.githubrepo.data.local.githubRepo.GithubRepoEntity
import com.batcuevasoft.githubrepo.data.local.githubRepo.GithubRepoEntityDao

@Database(
    version = 1,
    entities = [
        GithubRepoEntity::class
    ],
    exportSchema = true
)
abstract class GithubRepoDatabase : RoomDatabase() {
    abstract fun githubRepoEntityDao(): GithubRepoEntityDao
}