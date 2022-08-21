package com.hangrycoder.githubrequests

import com.hangrycoder.githubrequests.models.PullRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("repos/square/retrofit/pulls")
    suspend fun getPullRequests(
        @Query("state") state: String,
        @Query("page") page: Int
    ): NetworkResponse<List<PullRequest>, Any>
}