package com.hangrycoder.githubrequests

import android.app.Application
import com.hangrycoder.githubrequests.di.ApplicationComponent
import com.hangrycoder.githubrequests.di.DaggerApplicationComponent
import com.hangrycoder.githubrequests.di.NetworkModule

class MyApplication : Application() {

    private val BASE_URL = "https://api.github.com/"

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .networkModule(NetworkModule(BASE_URL))
            .build()
    }

}