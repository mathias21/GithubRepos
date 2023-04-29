package com.batcuevasoft.githubrepo.data.remote.githubRepo

import com.squareup.moshi.Json
import java.util.Date

data class GithubRepoRemoteEntity(
    val id: Long,
    val name: String,
    @Json(name = "created_at")
    val createdAt: Date,
    @Json(name = "updated_at")
    val updatedAt: Date,
    val description: String?,
    val forks: Int,
    @Json(name = "forks_count")
    val forkCount: Int,
    @Json(name = "full_name")
    val fullName: String,
    val owner: OwnerRemoteEntity,
    @Json(name = "stargazers_count")
    val starCount: Int,
    @Json(name = "html_url")
    val url: String,
    val language: String?
)

data class OwnerRemoteEntity(
    val id: Int,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "login")
    val username: String,
)