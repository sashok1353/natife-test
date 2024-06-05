package com.rakhlinskyi.natifeapp.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rakhlinskyi.natifeapp.core.data.local.GifsDatabase
import com.rakhlinskyi.natifeapp.core.data.models.GifEntity
import com.rakhlinskyi.natifeapp.core.data.paging.GifsRemoteMediator
import com.rakhlinskyi.natifeapp.core.data.paging.SearchPagingSource
import com.rakhlinskyi.natifeapp.core.data.remote.GifApi
import com.rakhlinskyi.natifeapp.core.utils.Constants.GIFS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class GifsRepository @Inject constructor(
    private val gifApi: GifApi,
    private val gifsDatabase: GifsDatabase
) {

    fun getAllGifs(): Flow<PagingData<GifEntity>> {
        val pagingSourceFactory = { gifsDatabase.gifsDao().getAllGifs() }
        return Pager(
            config = PagingConfig(pageSize = GIFS_PER_PAGE),
            remoteMediator = GifsRemoteMediator(
                gifApi = gifApi,
                gifDb = gifsDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun searchGifs(query: String): Flow<PagingData<GifEntity>> {
        return Pager(
            config = PagingConfig(pageSize = GIFS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(gifApi = gifApi, query = query)
            }
        ).flow
    }

    suspend fun removeGifById(gifId: String) {
        gifsDatabase.gifsDao().deleteGifById(gifId)
    }
}