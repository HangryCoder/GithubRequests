package com.hangrycoder.githubrequests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PullRequestViewModel(private val repository: RemoteRepository) : ViewModel() {

    private val _closedPullRequests by lazy {
        MutableLiveData<ApiState<List<PullRequest>>>()
    }
    val closedPullRequests: LiveData<ApiState<List<PullRequest>>> by lazy {
        _closedPullRequests
    }


    fun getClosedPullRequests() {
        viewModelScope.launch {
            _closedPullRequests.postValue(ApiState.Loading)
            when (val response = repository.getPullRequests("closed")) {
                is NetworkResponse.Success -> {
                    _closedPullRequests.postValue(ApiState.Success(response.body))
                }
                else -> {
                    _closedPullRequests.postValue(ApiState.Error(response.errorData))
                }
            }
        }
    }
}