package com.hangrycoder.githubrequests

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState

class PullRequestPagingSource(
    private val service: GithubApi,
    private val query: String
) : PagingSource<Int, PullRequest>() {

    val networkStatusLiveData = MutableLiveData<ApiState<Any>>()

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PullRequest> {
        // try {
        val nextPageNumber = params.key ?: 1

        if (nextPageNumber == 1) {
            networkStatusLiveData.value = ApiState.Loading
        }

        val response = service.getPullRequests(query, nextPageNumber)

        when (response) {
            is NetworkResponse.Success -> {
                val data = response.body
                networkStatusLiveData.value = ApiState.Success(data)
                return LoadResult.Page(
                    data = data,
                    prevKey = null,
                    nextKey = if (data.isEmpty()) null else nextPageNumber + 1
                )
            }
            is NetworkResponse.NetworkError -> {
                val error = response.error
               // networkStatusLiveData.value = ApiState.NetworkError(error)
                return LoadResult.Error(error)
            }
            is NetworkResponse.ApiError -> {
                //networkStatusLiveData.value = ApiState.ServerError(response.body, response.code)
                return LoadResult.Error(Exception())
            }
            is NetworkResponse.UnknownError -> {
                val error = response.error
               // networkStatusLiveData.value = ApiState.UnknownError(error)
                return LoadResult.Error(error!!)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PullRequest>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
