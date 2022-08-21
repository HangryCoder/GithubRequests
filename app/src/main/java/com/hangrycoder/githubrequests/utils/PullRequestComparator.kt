package com.hangrycoder.githubrequests.utils

import androidx.recyclerview.widget.DiffUtil
import com.hangrycoder.githubrequests.models.PullRequest

object PullRequestComparator : DiffUtil.ItemCallback<PullRequest>() {
    override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
        return oldItem == newItem
    }
}