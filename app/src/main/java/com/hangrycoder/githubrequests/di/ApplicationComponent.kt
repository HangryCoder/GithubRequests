package com.hangrycoder.githubrequests.di

import com.hangrycoder.githubrequests.networking.ApiService
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun getApiService(): ApiService
}