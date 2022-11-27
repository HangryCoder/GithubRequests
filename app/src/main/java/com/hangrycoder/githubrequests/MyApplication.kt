package com.hangrycoder.githubrequests

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

  /*  private val BASE_URL = "https://api.github.com/"

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .networkModule(NetworkModule(BASE_URL))
            .build()
    }
*/
}