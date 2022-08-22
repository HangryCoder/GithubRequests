package com.hangrycoder.githubrequests.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hangrycoder.githubrequests.di.ActivityScope
import com.hangrycoder.githubrequests.models.PullRequest
import com.hangrycoder.githubrequests.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityScope
class PullRequestViewModel @Inject constructor(repository: RemoteRepository) : ViewModel() {

    private val CLOSED_STATE = "closed"
    private val PAGE_SIZE = 10

    private val pullRequestPagingSource = repository.getPullRequests(CLOSED_STATE)

    val pullRequests: Flow<PagingData<PullRequest>> =
        Pager(config = PagingConfig(PAGE_SIZE), pagingSourceFactory = {
            pullRequestPagingSource
        }).flow.cachedIn(viewModelScope)
}