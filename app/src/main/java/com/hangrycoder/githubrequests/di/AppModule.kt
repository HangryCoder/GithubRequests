package com.hangrycoder.githubrequests.di

import com.hangrycoder.githubrequests.networking.ApiService
import com.hangrycoder.githubrequests.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRepository(service: ApiService): RemoteRepository = RemoteRepository(service)
}