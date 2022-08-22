package com.hangrycoder.githubrequests.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hangrycoder.githubrequests.models.PullRequest
import com.hangrycoder.githubrequests.networking.ApiService
import com.hangrycoder.githubrequests.utils.NoDataException
import okio.IOException
import retrofit2.HttpException

class PullRequestPagingSource(
    private val service: ApiService,
    private val query: String
) : PagingSource<Int, PullRequest>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PullRequest> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = service.getPullRequests(query, nextPageNumber)

            if (nextPageNumber == 1 && response.isEmpty()) {
                return LoadResult.Error(NoDataException())
            }
            return LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PullRequest>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
