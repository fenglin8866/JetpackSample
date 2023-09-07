package com.program.jetpack.sample.paging.sample

import androidx.paging.PagingSource
import androidx.paging.PagingState

class ExamplePagingSource(
    //提供数据的后台服务
    val backend: ExampleBackendService,
    //传递给后台服务的查询词
    val query: String
) : PagingSource<Int, User>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, User> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = backend.searchUsers(query, nextPageNumber)
            return LoadResult.Page(
                data = response.users,
                prevKey = null, // Only paging forward.
                nextKey = response.nextPageNumber
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
        }
        return  LoadResult.Error(Exception())
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
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