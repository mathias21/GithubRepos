package com.batcuevasoft.githubrepo.ui.main

import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepo
import com.batcuevasoft.githubrepo.core.githubRepo.RepoOwner
import java.util.Date
import kotlin.random.Random

object GithubRepoInstrumentation {

    fun getGithubRepo(
        id: Long = Random.nextLong(),
        name: String = "name$id"
    ) = GithubRepo(
        id,
        name,
        "fullname$id",
        "description$id",
        Random.nextInt(),
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
}