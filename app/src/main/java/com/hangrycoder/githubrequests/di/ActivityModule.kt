package com.hangrycoder.githubrequests.di

import com.hangrycoder.githubrequests.ui.adapter.PullRequestAdapter
import com.hangrycoder.githubrequests.utils.PullRequestComparator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun provideAdapter(): PullRequestAdapter = PullRequestAdapter(PullRequestComparator)
}