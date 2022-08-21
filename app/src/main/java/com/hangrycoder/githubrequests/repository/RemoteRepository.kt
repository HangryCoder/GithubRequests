package com.hangrycoder.githubrequests.repository

import com.hangrycoder.githubrequests.ApiService
import com.hangrycoder.githubrequests.PullRequestPagingSource

class RemoteRepository(private val service: ApiService) {
    fun getPullRequests(state: String) = PullRequestPagingSource(service, state)
}