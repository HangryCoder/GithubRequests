package com.hangrycoder.githubrequests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PullRequestViewModel(private val repository: RemoteRepository) : ViewModel() {

    fun getClosedPullRequests() {
        viewModelScope.launch {
            repository.getPullRequests("closed")
        }
    }
}