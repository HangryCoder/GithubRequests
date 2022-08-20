package com.hangrycoder.githubrequests

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class PullRequest(
    val title: String? = null,
    val user: User? = null,
    @Json(name = "created_at")
    val createdAt: String? = null,
    @Json(name = "closed_at")
    val closedAt: String? = null
)
