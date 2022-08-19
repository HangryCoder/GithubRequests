package com.hangrycoder.githubrequests

import com.google.gson.annotations.SerializedName

data class PullRequest(
    val title: String? = null,
    val user: User? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("closed_at")
    val closedAt: String? = null
)
