package com.hangrycoder.githubrequests

import com.squareup.moshi.Json

data class PullRequest(
    val id: Int? = null,
    val title: String? = null,
    val user: User? = null,
    @Json(name = "created_at")
    val createdAt: String? = null,
    @Json(name = "closed_at")
    val closedAt: String? = null
)
