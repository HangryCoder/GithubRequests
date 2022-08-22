package com.hangrycoder.githubrequests.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hangrycoder.githubrequests.models.PullRequest
import com.hangrycoder.githubrequests.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PullRequestViewModel @Inject constructor(repository: RemoteRepository) : ViewModel() {

    private val pullRequestPagingSource = repository.getPullRequests("closed")

    val pullRequests: Flow<PagingData<PullRequest>> =
        Pager(config = PagingConfig(5), pagingSourceFactory = {
            pullRequestPagingSource
        }).flow.cachedIn(viewModelScope)
}