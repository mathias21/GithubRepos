package com.batcuevasoft.githubrepo.data.remote.githubRepo

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("users/{username}/repos")
    suspend fun getUserRepositories(
        @Path("username") username: String
    ): List<GithubRepoRemoteEntity>

    @GET("search/repositories?sort=stars&direction=desc")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("per_page") perPageCount: Int,
        @Query("page") page: Int,
    ): GithubRepoPageResponse

}