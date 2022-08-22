package com.hangrycoder.githubrequests.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hangrycoder.githubrequests.networking.ApiService
import com.hangrycoder.githubrequests.repository.RemoteRepository
import com.hangrycoder.githubrequests.viewmodel.PullRequestViewModel
import com.hangrycoder.githubrequests.viewmodel.PullRequestViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideRepository(service: ApiService): RemoteRepository = RemoteRepository(service)

    @Provides
    fun provideViewModelFactory(remoteRepository: RemoteRepository): PullRequestViewModelFactory {
        return PullRequestViewModelFactory(remoteRepository)
    }

    @Provides
    @ActivityScope
    fun provideViewModel(
        activity: AppCompatActivity,
        pullRequestViewModelFactory: PullRequestViewModelFactory
    ): PullRequestViewModel {
        return ViewModelProvider(
            activity.viewModelStore,
            pullRequestViewModelFactory
        )[PullRequestViewModel::class.java]
    }
}