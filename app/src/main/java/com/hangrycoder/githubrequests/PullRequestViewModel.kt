package com.hangrycoder.githubrequests

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class PullRequestViewModel(private val repository: RemoteRepository) : ViewModel() {

    private val _closedPullRequests by lazy {
        MutableLiveData<ApiState<List<PullRequest>>>()
    }
    val closedPullRequests: LiveData<ApiState<List<PullRequest>>> by lazy {
        _closedPullRequests
    }

    fun getClosedPullRequests() {
        val pullRequests: Flow<PagingData<PullRequest>> =
            Pager(config = PagingConfig(20), pagingSourceFactory = {
                repository.getPullRequests("closed")
            }).flow.cachedIn(viewModelScope)
    }


    /* fun getClosedPullRequests() {
         viewModelScope.launch(Dispatchers.IO) {
             _closedPullRequests.postValue(ApiState.Loading)
             when (val response = repository.getPullRequests("closed")) {
                 is NetworkResponse.Success -> {
                     // Log.e("ViewModel","response ${response.body}")
                     _closedPullRequests.postValue(ApiState.Success(response.body))
                 }
                 is NetworkResponse.ApiError -> {

                 }
                 is NetworkResponse.NetworkError -> {

                 }
                 is NetworkResponse.UnknownError -> {
                    // _closedPullRequests.postValue(ApiState.Error(response))
                 }
             }
         }
     }*/
}