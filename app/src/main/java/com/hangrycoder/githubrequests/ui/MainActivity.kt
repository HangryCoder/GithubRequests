package com.hangrycoder.githubrequests.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.hangrycoder.githubrequests.ui.adapter.LoaderStateAdapter
import com.hangrycoder.githubrequests.ui.adapter.PullRequestAdapter
import com.hangrycoder.githubrequests.utils.PullRequestComparator
import com.hangrycoder.githubrequests.R
import com.hangrycoder.githubrequests.databinding.ActivityMainBinding
import com.hangrycoder.githubrequests.networking.ApiClient
import com.hangrycoder.githubrequests.networking.ApiService
import com.hangrycoder.githubrequests.repository.RemoteRepository
import com.hangrycoder.githubrequests.utils.SpaceItemDecoration
import com.hangrycoder.githubrequests.viewmodel.PullRequestViewModel
import com.hangrycoder.githubrequests.viewmodel.PullRequestViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = PullRequestAdapter(PullRequestComparator)

    private val viewModel: PullRequestViewModel by viewModels {
        PullRequestViewModelFactory(
            RemoteRepository(ApiClient.getClient().create(ApiService::class.java))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            }
        }
    }
}