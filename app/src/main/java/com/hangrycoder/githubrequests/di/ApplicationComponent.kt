package com.hangrycoder.githubrequests.di

import com.hangrycoder.githubrequests.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ActivityModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}