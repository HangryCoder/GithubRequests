package com.hangrycoder.githubrequests.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hangrycoder.githubrequests.networking.ApiService
import com.hangrycoder.githubrequests.repository.RemoteRepository
import com.hangrycoder.githubrequests.ui.adapter.PullRequestAdapter
import com.hangrycoder.githubrequests.utils.PullRequestComparator
import com.hangrycoder.githubrequests.viewmodel.PullRequestViewModel
import com.hangrycoder.githubrequests.viewmodel.PullRequestViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun provideRepository(service: ApiService): RemoteRepository = RemoteRepository(service)

    @Provides
    fun provideAdapter(): PullRequestAdapter = PullRequestAdapter(PullRequestComparator)

   /* @Provides
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
    }*/
}