package com.hangrycoder.githubrequests.repository

import com.hangrycoder.githubrequests.networking.ApiService
import com.hangrycoder.githubrequests.PullRequestPagingSource

class RemoteRepository(private val service: ApiService) {
    fun getPullRequests(state: String) = PullRequestPagingSource(service, state)
}