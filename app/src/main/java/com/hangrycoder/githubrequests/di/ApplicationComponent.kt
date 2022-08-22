package com.hangrycoder.githubrequests.di

import com.hangrycoder.githubrequests.ui.MainActivity
import dagger.Component

@Component
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}