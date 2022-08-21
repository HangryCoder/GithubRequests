package com.hangrycoder.githubrequests

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class PullRequestViewModel(private val repository: RemoteRepository) : ViewModel() {

    val pullRequests: Flow<PagingData<PullRequest>> =
        Pager(config = PagingConfig(20), pagingSourceFactory = {
            repository.getPullRequests("closed")
        }).flow.cachedIn(viewModelScope)
}