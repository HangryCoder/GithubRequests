package com.hangrycoder.githubrequests

import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("repos/square/retrofit/pulls")
    suspend fun getPullRequests(
        @Query("state") state: String,
        @Query("page") page: Int
    ): NetworkResponse<List<PullRequest>, Any>
}