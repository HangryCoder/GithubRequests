package com.hangrycoder.githubrequests.repository

import com.hangrycoder.githubrequests.networking.ApiService
import com.hangrycoder.githubrequests.paging.PullRequestPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(val service: ApiService) {
    fun getPullRequests(state: String) = PullRequestPagingSource(service, state)
}