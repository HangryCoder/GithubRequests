package com.hangrycoder.githubrequests.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hangrycoder.githubrequests.R
import com.hangrycoder.githubrequests.databinding.LoadStateItemBinding

class LoaderStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoaderStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = LoadStateViewHolder(parent)

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)

    inner class LoadStateViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.load_state_item, parent, false)
    ) {
        private val binding = LoadStateItemBinding.bind(itemView)
        private val progressBar: ProgressBar = binding.progressBar
        private val errorLayoutGroup: Group = binding.errorLayoutGroup
        private val tryAgainButton: AppCompatButton = binding.tryAgainButton

        fun bind(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
            errorLayoutGroup.isVisible = loadState is LoadState.Error
            tryAgainButton.setOnClickListener {
                retry()
            }
        }
    }
}
