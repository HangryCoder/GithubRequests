package com.hangrycoder.githubrequests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PullRequestAdapter : RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder>() {

    var pullRequests: List<PullRequest>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_pull_request_item, parent, false)
        return PullRequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val pullRequest = pullRequests?.get(position)
        with(holder) {
            authorNameTextView.text = "Author ${pullRequest?.user?.name}"
            titleTextView.text = pullRequest?.title
            createdAtTextView.text = "Created at ${pullRequest?.createdAt}"
            closedAtTextView.text = "Closed at ${pullRequest?.closedAt}"

            Glide.with(authorImageView.context)
                .load(pullRequest?.user?.avatarUrl)
                .into(authorImageView)
        }
    }

    override fun getItemCount(): Int = pullRequests?.size ?: 0

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
                createdAtTextView = findViewById(R.id.pull_request_created_at)
                closedAtTextView = findViewById(R.id.pull_request_closed_at)
            }
        }
    }
}