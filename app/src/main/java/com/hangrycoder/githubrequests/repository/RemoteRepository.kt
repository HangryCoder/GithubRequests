package com.hangrycoder.githubrequests.repository

import com.hangrycoder.githubrequests.networking.ApiService
import com.hangrycoder.githubrequests.paging.PullRequestPagingSource
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val service: ApiService) {
    fun getPullRequests(state: String) = PullRequestPagingSource(service, state)
}