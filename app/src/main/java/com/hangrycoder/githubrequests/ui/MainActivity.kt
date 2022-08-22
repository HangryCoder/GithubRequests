package com.hangrycoder.githubrequests.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.bumptech.glide.Glide
import com.hangrycoder.githubrequests.MyApplication
import com.hangrycoder.githubrequests.ui.adapter.LoaderStateAdapter
import com.hangrycoder.githubrequests.ui.adapter.PullRequestAdapter
import com.hangrycoder.githubrequests.R
import com.hangrycoder.githubrequests.databinding.ActivityMainBinding
import com.hangrycoder.githubrequests.di.ActivityComponent
import com.hangrycoder.githubrequests.utils.NoDataException
import com.hangrycoder.githubrequests.utils.SpaceItemDecoration
import com.hangrycoder.githubrequests.viewmodel.PullRequestViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var adapter: PullRequestAdapter

    @Inject
    lateinit var viewModel: PullRequestViewModel

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityComponent =
            (applicationContext as MyApplication).appComponent?.activityComponent()?.create()!!
        activityComponent.inject(this)

        initialSetup()
    }

    private fun initialSetup() {
        setupRecyclerView()
        fetchClosedPullRequests()
        paginationLoadListener()
        tryAgainClickListener()
    }

    private fun tryAgainClickListener() {
        binding.errorLayout.tryAgainButton.setOnClickListener {
            adapter.retry()
        }
    }

    private fun setupRecyclerView() {
        val divider = SpaceItemDecoration(resources.getInteger(R.integer.divider_space))
        binding.recyclerView.adapter =
            adapter.withLoadStateFooter(footer = LoaderStateAdapter(adapter::retry))
        binding.recyclerView.addItemDecoration(divider)
    }

    private fun fetchClosedPullRequests() {
        lifecycleScope.launchWhenStarted {
            viewModel.pullRequests.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun paginationLoadListener() {
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadState ->
                binding.progressBar.isVisible = loadState.refresh is LoadState.Loading
                binding.errorLayout.root.isVisible = loadState.refresh is LoadState.Error
                errorHandling(loadState.refresh, binding)
            }
        }
    }

    private fun errorHandling(loadState: LoadState, binding: ActivityMainBinding) {
        val errorImageView = binding.errorLayout.errorImage
        val errorMessage = binding.errorLayout.errorMessage
        val tryAgainButton = binding.errorLayout.tryAgainButton
        var errorIcon: Int? = null
        if (loadState is LoadState.Error) {
            when (loadState.error) {
                is IOException -> {
                    errorMessage.text =
                        errorMessage.context.resources.getString(R.string.no_internet_connection)
                    errorIcon = R.drawable.ic_network_error
                    tryAgainButton.isVisible = true
                }
                is HttpException -> {
                    errorMessage.text =
                        errorMessage.context.resources.getString(R.string.server_error)
                    errorIcon = R.drawable.ic_server_error
                    tryAgainButton.isVisible = true
                }
                is NoDataException -> {
                    errorMessage.text =
                        errorMessage.context.resources.getString(R.string.no_pull_requests_found)
                    errorIcon = R.drawable.ic_no_results
                    tryAgainButton.isVisible = false
                }
                else -> {
                    errorMessage.text =
                        errorMessage.context.resources.getString(R.string.error_message)
                    errorIcon = R.drawable.ic_server_error
                    tryAgainButton.isVisible = true
                }
            }
        }
        Glide.with(errorImageView.context)
            .load(errorIcon)
            .placeholder(R.drawable.ic_server_error)
            .into(errorImageView)
    }
}