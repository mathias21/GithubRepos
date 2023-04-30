package com.batcuevasoft.ui

import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepo
import com.batcuevasoft.githubrepo.core.githubRepo.RepoOwner
import com.batcuevasoft.githubrepo.data.local.githubRepo.GithubRepoEntity
import com.batcuevasoft.githubrepo.data.remote.githubRepo.GithubRepoRemoteEntity
import com.batcuevasoft.githubrepo.data.remote.githubRepo.OwnerRemoteEntity
import java.util.Date
import kotlin.random.Random

object GithubRepoInstrumentation {

    fun getGithubRepo(
        id: Long = Random.nextLong(),
        name: String = "name$id",
        starCount: Int = Random.nextInt(),
    ) = GithubRepo(
        id,
        name,
        "fullname$id",
        "description$id",
        starCount,
        Date(),
        Random.nextInt(),
        "repourl",
        Random.nextInt(),
        RepoOwner(
            "ownerName",
            null
        ),
        "Kotlin"
    )

    fun getGithubRepoEntity(
        id: Long = Random.nextLong(),
        name: String = "name$id",
        starCount: Int = Random.nextInt(),
    ) = GithubRepoEntity(
        id,
        name,
        "fullname$id",
        "description$id",
        starCount,
        Date().time,
        "repourl",
        Random.nextInt(),
        "ownerName",
        null,
        "Kotlin"
    )

    fun getGithubRepoRemoteEntity(
        id: Long = Random.nextLong(),
        name: String = "name$id",
        starCount: Int = Random.nextInt(),
    ) = GithubRepoRemoteEntity(
        id,
        name,
        Date(),
        Date(),
        "description$id",
        12,
        12,
        "fullname",
        OwnerRemoteEntity(
            1,
            "avatarUrl",
            "name"
        ),
        starCount,
        "repourl",
        "kotlin"
    )
}