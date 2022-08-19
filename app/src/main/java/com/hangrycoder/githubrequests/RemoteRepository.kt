package com.hangrycoder.githubrequests

import retrofit2.Call

class RemoteRepository(private val api: GithubApi) {

   suspend fun getPullRequests(state: String): Call<List<PullRequest>> {
       return api.getPullRequests(state)
    }
}