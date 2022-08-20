package com.hangrycoder.githubrequests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PullRequestViewModelFactory(private val remoteRepository: RemoteRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PullRequestViewModel(remoteRepository) as T
    }
}