package com.hangrycoder.githubrequests.di

import com.hangrycoder.githubrequests.networking.ApiService
import com.hangrycoder.githubrequests.repository.RemoteRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideRepository(service: ApiService): RemoteRepository = RemoteRepository(service)
}