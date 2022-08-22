package com.hangrycoder.githubrequests.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/*@Module
class AppModule(mApplication: Application) {
    private val mApplication: Application

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }

    init {
        this.mApplication = mApplication
    }
}*/

//@Module
//class AppModule {
//
//    @Provides
//    fun provideRepository(service: ApiService): RemoteRepository = RemoteRepository(service)
//}