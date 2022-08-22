package com.hangrycoder.githubrequests.di

import androidx.lifecycle.ViewModel
import com.hangrycoder.githubrequests.viewmodel.PullRequestViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PullRequestViewModel::class)
    abstract fun bindViewModel(viewmodel: PullRequestViewModel): ViewModel
}