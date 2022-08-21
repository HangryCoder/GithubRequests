package com.hangrycoder.githubrequests.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hangrycoder.githubrequests.RemoteRepository

class PullRequestViewModelFactory(private val remoteRepository: RemoteRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PullRequestViewModel(remoteRepository) as T
    }
}