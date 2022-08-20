package com.hangrycoder.githubrequests

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hangrycoder.githubrequests.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /*  private val remoteRepository: RemoteRepository by lazy {
          RemoteRepository(ApiClient.getClient().create(GithubApi::class.java))
      }*/
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
    }

    private fun fetchClosedPullRequests() {
        viewModel.getClosedPullRequests()
        viewModel.closedPullRequests.observe(this) { pullRequests ->
            Log.e("TAG", "fetchClosedPullRequests: $pullRequests")
        }
    }
}