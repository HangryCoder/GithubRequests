package com.hangrycoder.githubrequests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hangrycoder.githubrequests.models.PullRequest

class PullRequestAdapter(diffCallback: DiffUtil.ItemCallback<PullRequest>) :
    PagingDataAdapter<PullRequest, PullRequestAdapter.PullRequestViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_pull_request_item, parent, false)
        return PullRequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val pullRequest = getItem(position)//pullRequests?.get(position)
        with(holder) {
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

    inner class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val authorNameTextView: AppCompatTextView
        val authorImageView: AppCompatImageView
        val titleTextView: AppCompatTextView
        val createdAtTextView: AppCompatTextView
        val closedAtTextView: AppCompatTextView

        init {
            with(itemView) {
                authorNameTextView = findViewById(R.id.pull_request_user_name)
                authorImageView = findViewById(R.id.pull_request_user_image)
                titleTextView = findViewById(R.id.pull_request_title)
                createdAtTextView = findViewById(R.id.created_date)
                closedAtTextView = findViewById(R.id.closed_date)
            }
        }
    }
}