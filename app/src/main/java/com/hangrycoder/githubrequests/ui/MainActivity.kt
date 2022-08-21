package com.hangrycoder.githubrequests.ui

import android.os.Bundle
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

        fetchClosedPullRequests()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val divider = SpaceItemDecoration(resources.getInteger(R.integer.divider_space))
        binding.recyclerView.adapter = adapter.withLoadStateFooter(footer = LoaderStateAdapter())
        binding.recyclerView.addItemDecoration(divider)
    }

    private fun fetchClosedPullRequests() {
        lifecycleScope.launchWhenStarted {
            viewModel.pullRequests.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadState ->
                binding.progressBar.isVisible = loadState.refresh is LoadState.Loading
               // binding.errorLayout.root.isVisible = loadState.refresh is LoadState.Error
            }
        }

        /* viewModel.getNetworkStatus().observe(this) {
             when (it) {
                 is ApiState.Loading -> {
                     binding.progressBar.isVisible = true
                 }
                 is ApiState.Success -> {
                     Log.e("TAG", "Network Status: Success")
                     binding.progressBar.isVisible = false
                 }
                 is ApiState.NetworkError -> {
                     Log.e("TAG", "Network Status: Network Error ${it.error}")
                     binding.progressBar.isVisible = false
                 }
                 is ApiState.ServerError -> {
                     Log.e("TAG", "ServerError Status: Server Error ${it.error}")
                     binding.progressBar.isVisible = false
                 }
                 is ApiState.UnknownError -> {
                     Log.e("TAG", "Unknown Status: Unknown Error ${it.error}")
                     binding.progressBar.isVisible = false
                 }
             }
         }*/
    }
}