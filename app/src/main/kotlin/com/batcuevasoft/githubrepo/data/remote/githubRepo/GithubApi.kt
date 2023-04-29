package com.batcuevasoft.githubrepo.data.remote.githubRepo

import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users/{username}/repos")
    suspend fun getUserRepositories(@Path("username") username: String): List<GithubRepoRemoteEntity>

}