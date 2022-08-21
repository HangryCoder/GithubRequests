package com.hangrycoder.githubrequests.repository

import com.hangrycoder.githubrequests.networking.ApiService
import com.hangrycoder.githubrequests.paging.PullRequestPagingSource

class RemoteRepository(private val service: ApiService) {
    fun getPullRequests(state: String) = PullRequestPagingSource(service, state)
}