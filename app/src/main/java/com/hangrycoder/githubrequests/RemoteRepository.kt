package com.hangrycoder.githubrequests

class RemoteRepository(private val api: GithubApi) {
    fun getPullRequests(state: String) = PullRequestPagingSource(api, state)
}