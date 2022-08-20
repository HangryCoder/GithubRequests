package com.hangrycoder.githubrequests

class RemoteRepository(private val api: GithubApi) {
    suspend fun getPullRequests(state: String) = api.getPullRequests(state)
}