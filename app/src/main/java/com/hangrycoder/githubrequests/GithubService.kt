package com.hangrycoder.githubrequests

import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("repos/square/retrofit/pulls")
    fun getPullRequests(@Query("state") state: String)
}