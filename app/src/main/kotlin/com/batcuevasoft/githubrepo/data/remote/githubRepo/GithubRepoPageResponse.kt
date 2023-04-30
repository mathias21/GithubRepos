package com.batcuevasoft.githubrepo.data.remote.githubRepo

import com.squareup.moshi.Json

data class GithubRepoPageResponse(
    @Json(name = "total_count")
    val total: Int = 0,
    @Json(name = "items")
    val items: List<GithubRepoRemoteEntity> = emptyList(),
    @Json(name = "incomplete_results")
    val isIncomplete: Boolean
)