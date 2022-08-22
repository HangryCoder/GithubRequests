package com.hangrycoder.githubrequests.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class,
        SubcomponentsModule::class,
        ViewModelBuilderModule::class,
        AppModule::class]
)
interface ApplicationComponent {
    fun activityComponent(): ActivityComponent.Factory
}