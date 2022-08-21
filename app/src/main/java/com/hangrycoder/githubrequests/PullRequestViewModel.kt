package com.hangrycoder.githubrequests

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class PullRequestViewModel(repository: RemoteRepository) : ViewModel() {

    private val pullRequestPagingSource = repository.getPullRequests("closed")

    val pullRequests: Flow<PagingData<PullRequest>> =
        Pager(config = PagingConfig(30), pagingSourceFactory = {
            pullRequestPagingSource
        }).flow.cachedIn(viewModelScope)

    fun getNetworkStatus() = pullRequestPagingSource.networkStatusLiveData
}