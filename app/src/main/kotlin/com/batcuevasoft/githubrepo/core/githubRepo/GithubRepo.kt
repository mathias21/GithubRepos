package com.batcuevasoft.githubrepo.core.githubRepo

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
)

data class RepoOwner(
    val name: String,
    val avatarUrl: String? = null
)