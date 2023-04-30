package com.batcuevasoft.githubrepo.data.remote.githubRepo

import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepo
import com.batcuevasoft.githubrepo.core.githubRepo.RepoOwner
import com.squareup.moshi.Json
import org.joda.time.Interval
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
    val language: String?,
) {
    fun toGithubRepo(): GithubRepo {
        val daysSinceUpdate = try {
            Interval(updatedAt.time, Date().time).toDuration().standardDays.toInt()
        } catch (e: Exception) {
            0
        }
        return GithubRepo(
            id,
            name = name,
            fullName = fullName,
            description = description.orEmpty(),
            starCount = starCount,
            repoUrl = url,
            forkCount = forkCount,
            lastUpdateDate = Date(updatedAt.time),
            daysSinceUpdate = daysSinceUpdate,
            owner = owner.toRepoOwner(),
            language = language
        )
    }
}

data class OwnerRemoteEntity(
    val id: Int,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "login")
    val username: String,
) {
    fun toRepoOwner() = RepoOwner(
        username,
        avatarUrl
    )
}