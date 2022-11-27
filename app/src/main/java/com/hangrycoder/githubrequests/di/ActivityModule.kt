package com.hangrycoder.githubrequests.di

import com.hangrycoder.githubrequests.ui.adapter.PullRequestAdapter
import com.hangrycoder.githubrequests.utils.PullRequestComparator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun provideAdapter(): PullRequestAdapter = PullRequestAdapter(PullRequestComparator)
}