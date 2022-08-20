package com.hangrycoder.githubrequests

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "avatar_url")
    val avatarUrl: String? = null,
    @Json(name = "login")
    val name: String? = null
)
