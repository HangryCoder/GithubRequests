package com.hangrycoder.githubrequests.repository

import com.hangrycoder.githubrequests.GithubApi
import com.hangrycoder.githubrequests.PullRequestPagingSource

class RemoteRepository(private val api: GithubApi) {
    fun getPullRequests(state: String) = PullRequestPagingSource(api, state)
}