package com.rakhlinskyi.natifeapp.core.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rakhlinskyi.natifeapp.core.data.mappers.toGifEntity
import com.rakhlinskyi.natifeapp.core.data.models.GifEntity
import com.rakhlinskyi.natifeapp.core.data.remote.GifApi
import com.rakhlinskyi.natifeapp.core.utils.Constants.GIFS_PER_PAGE

class SearchPagingSource(
    private val gifApi: GifApi,
    private val query: String
) : PagingSource<Int, GifEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifEntity> {
        val currentPage = params.key ?: 0
        return try {
            val response = gifApi.searchGifs(query = query, perPage = GIFS_PER_PAGE, page = currentPage).data
            val endOfPaginationReached = response.isEmpty()
            if (response.isNotEmpty()) {
                LoadResult.Page(
                    data = response.map {it.toGifEntity()},
                    prevKey = if (currentPage == 0) null else currentPage - 20,
                    nextKey = if (endOfPaginationReached) null else currentPage + 20
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifEntity>): Int? {
        return state.anchorPosition
    }

}