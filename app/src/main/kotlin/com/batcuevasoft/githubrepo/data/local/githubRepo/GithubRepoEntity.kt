package com.batcuevasoft.githubrepo.data.local.githubRepo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepo
import com.batcuevasoft.githubrepo.core.githubRepo.RepoOwner
import org.joda.time.Interval
import java.util.Date

const val GITHUB_REPO_ENTITY_TABLE = "GithubRepoEntityTable"

@Entity(
    tableName = GITHUB_REPO_ENTITY_TABLE
)
data class GithubRepoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val fullName: String,
    val description: String,
    val stars: Int,
    val lastUpdateTimestamp: Long,
    val repoUrl: String,
    val forkCount: Int,
    val authorName: String,
    val avatarUrl: String? = null,
    val language: String? = null
) {
    fun toGithubRepo(): GithubRepo {
        val daysSinceUpdate = Interval(lastUpdateTimestamp, Date().time).toDuration().standardDays.toInt()
        return GithubRepo(
            id = id,
            name = name,
            fullName = fullName,
            description = description,
            starCount = stars,
            lastUpdateDate = Date(lastUpdateTimestamp),
            daysSinceUpdate = daysSinceUpdate,
            repoUrl = repoUrl,
            forkCount = forkCount,
            owner = RepoOwner(
                authorName,
                avatarUrl
            ),
            language = language
        )
    }
}