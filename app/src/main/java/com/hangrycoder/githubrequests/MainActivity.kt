package com.hangrycoder.githubrequests

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hangrycoder.githubrequests.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = PullRequestAdapter(PullRequestComparator)

    private val viewModel: PullRequestViewModel by viewModels {
        PullRequestViewModelFactory(
            RemoteRepository(ApiClient.getClient().create(GithubApi::class.java))
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
        val divider = SpaceItemDecoration(32)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(divider)
    }

    private fun fetchClosedPullRequests() {
        lifecycleScope.launchWhenStarted {
            viewModel.pullRequests.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        viewModel.getNetworkStatus().observe(this) {
            when (it) {
                is ApiState.Loading -> {
                    Log.e("TAG", "Network Status: Loading")
                }
                is ApiState.Success -> {
                    Log.e("TAG", "Network Status: Success")
                }
                is ApiState.NetworkError -> {
                    Log.e("TAG", "Network Status: Network Error ${it.error}")
                }
                is ApiState.ServerError -> {
                    Log.e("TAG", "Network Status: Server Error ${it.errorCode}")
                }
                is ApiState.UnknownError -> {
                    Log.e("TAG", "Network Status: Unknown Error ${it.error}")
                }
                else -> {
                    Log.e("TAG", "Blaaah")
                }
            }
        }
    }
}