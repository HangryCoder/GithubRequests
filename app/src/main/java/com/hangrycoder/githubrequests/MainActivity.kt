package com.hangrycoder.githubrequests

import android.os.Bundle
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
        // viewModel.getClosedPullRequests()
        lifecycleScope.launchWhenStarted {
            viewModel.pullRequests.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
        /* viewModel.closedPullRequests.observe(this) { response ->
             when (response) {
                 is ApiState.Success -> {
                     val pullRequests = response.data
                     Log.e("TAG", "fetchClosedPullRequests: ${pullRequests.size}")
                     //adapter.pullRequests = pullRequests
                 }
                 is ApiState.Loading -> {
                     //Do Something
                 }
                 else -> {
                     //Show error state
                 }
             }
         }*/
    }
}