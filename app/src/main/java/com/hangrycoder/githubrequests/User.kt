package com.hangrycoder.githubrequests

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("login")
    val name: String? = null
)
