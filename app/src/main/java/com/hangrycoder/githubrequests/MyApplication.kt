package com.hangrycoder.githubrequests

import android.app.Application
import com.hangrycoder.githubrequests.di.ApplicationComponent
import com.hangrycoder.githubrequests.di.DaggerApplicationComponent
import com.hangrycoder.githubrequests.di.NetworkModule

class MyApplication : Application() {

    private val BASE_URL = "https://api.github.com/"
    var appComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
            .networkModule(NetworkModule(BASE_URL))
            .build()
    }
}