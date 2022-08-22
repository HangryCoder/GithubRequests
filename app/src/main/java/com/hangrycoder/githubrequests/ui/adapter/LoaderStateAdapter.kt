package com.hangrycoder.githubrequests.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hangrycoder.githubrequests.R
import com.hangrycoder.githubrequests.databinding.LoadStateItemBinding
import okio.IOException
import retrofit2.HttpException

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
        private val errorMessage: AppCompatTextView = binding.errorMessage
        private val errorImageView: AppCompatImageView = binding.errorImage
        private val tryAgainButton: AppCompatButton = binding.tryAgainButton

        fun bind(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
            errorLayoutGroup.isVisible = loadState is LoadState.Error
            errorHandling(loadState)
            tryAgainButton.setOnClickListener {
                retry()
            }
        }

        private fun errorHandling(loadState: LoadState) {
            var errorIcon: Int? = null
            if (loadState is LoadState.Error) {
                when (loadState.error) {
                    is IOException -> {
                        errorMessage.text =
                            errorMessage.context.resources.getString(R.string.no_internet_connection)
                        errorIcon = R.drawable.ic_network_error
                    }
                    is HttpException -> {
                        errorMessage.text =
                            errorMessage.context.resources.getString(R.string.server_error)
                        errorIcon = R.drawable.ic_server_error
                    }
                    else -> {
                        errorMessage.text =
                            errorMessage.context.resources.getString(R.string.error_message)
                        errorIcon = R.drawable.ic_server_error
                    }
                }
            }
            Glide.with(errorImageView.context)
                .load(errorIcon)
                .placeholder(R.drawable.ic_server_error)
                .into(errorImageView)
        }
    }
}
