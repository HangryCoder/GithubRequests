package com.hangrycoder.githubrequests

import com.squareup.moshi.Json

data class User(
    @Json(name = "avatar_url")
    val avatarUrl: String? = null,
    @Json(name = "login")
    val name: String? = null
)
