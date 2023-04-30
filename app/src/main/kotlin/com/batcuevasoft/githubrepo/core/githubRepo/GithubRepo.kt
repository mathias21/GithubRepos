package com.batcuevasoft.githubrepo.core.githubRepo

import com.batcuevasoft.githubrepo.data.local.githubRepo.GithubRepoEntity
import java.util.Date

data class GithubRepo(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String,
    val starCount: Int,
    val lastUpdateDate: Date,
    val daysSinceUpdate: Int,
    val repoUrl: String,
    val forkCount: Int,
    val owner: RepoOwner,
    val language: String? = null
) {
    fun toGithubRepoEntity() = GithubRepoEntity(
        id = id,
        name = name,
        fullName = fullName,
        description = description,
        stars = starCount,
        repoUrl = repoUrl,
        forkCount = forkCount,
        lastUpdateTimestamp = lastUpdateDate.time,
        authorName = owner.name,
        avatarUrl = owner.avatarUrl,
        language = language
    )
}

data class RepoOwner(
    val name: String,
    val avatarUrl: String? = null
)