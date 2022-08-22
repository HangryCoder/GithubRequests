package com.hangrycoder.githubrequests.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hangrycoder.githubrequests.R
import com.hangrycoder.githubrequests.databinding.PullRequestItemBinding
import com.hangrycoder.githubrequests.models.PullRequest
import com.hangrycoder.githubrequests.utils.DateUtil

class PullRequestAdapter(diffCallback: DiffUtil.ItemCallback<PullRequest>) :
    PagingDataAdapter<PullRequest, PullRequestAdapter.PullRequestViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pull_request_item, parent, false)
        return PullRequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val pullRequest = getItem(position)
        holder.bind(pullRequest)
    }

    inner class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = PullRequestItemBinding.bind(itemView)
        private val authorNameTextView: AppCompatTextView = binding.pullRequestUserName
        private val authorImageView: AppCompatImageView = binding.pullRequestUserImage
        private val titleTextView: AppCompatTextView = binding.pullRequestTitle
        private val createdAtTextView: AppCompatTextView = binding.createdDate
        private val closedAtTextView: AppCompatTextView = binding.closedDate

        fun bind(pullRequest: PullRequest?) {
            authorNameTextView.text = pullRequest?.user?.name
            titleTextView.text = pullRequest?.title
            createdAtTextView.text = DateUtil.convertTimestampToDate(pullRequest?.createdAt)
            closedAtTextView.text = DateUtil.convertTimestampToDate(pullRequest?.closedAt)

            Glide.with(authorImageView.context)
                .load(pullRequest?.user?.avatarUrl)
                .circleCrop()
                .placeholder(R.drawable.avatar_placeholder)
                .into(authorImageView)
        }
    }
}