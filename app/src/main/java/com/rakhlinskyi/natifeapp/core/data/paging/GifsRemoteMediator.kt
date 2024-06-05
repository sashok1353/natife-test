package com.rakhlinskyi.natifeapp.core.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rakhlinskyi.natifeapp.core.data.local.GifsDatabase
import com.rakhlinskyi.natifeapp.core.data.mappers.toGifEntity
import com.rakhlinskyi.natifeapp.core.data.models.GifEntity
import com.rakhlinskyi.natifeapp.core.data.models.GifRemoteKeysEntity
import com.rakhlinskyi.natifeapp.core.data.remote.GifApi
import com.rakhlinskyi.natifeapp.core.utils.Constants.GIFS_PER_PAGE

@ExperimentalPagingApi
class GifsRemoteMediator(
    private val gifApi: GifApi,
    private val gifDb: GifsDatabase
) : RemoteMediator<Int, GifEntity>() {

    private val gifDao = gifDb.gifsDao()
    private val gifRemoteKeysDao = gifDb.gifsRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GifEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(20) ?: 0
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                }
            }

            val response = gifApi.getAllGifs(page = currentPage, perPage = GIFS_PER_PAGE).data
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 0) null else currentPage - 20
            val nextPage = if (endOfPaginationReached) null else currentPage + 20

            gifDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    gifDao.deleteAllGifs()
                    gifRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.map { gif ->
                   GifRemoteKeysEntity(
                        id = gif.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                gifRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                gifDao.addGifs(gifs = response.map {gifDto -> gifDto.toGifEntity()})
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, GifEntity>
    ): GifRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                gifRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, GifEntity>
    ): GifRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { gif ->
                gifRemoteKeysDao.getRemoteKeys(id = gif.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, GifEntity>
    ): GifRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { gif ->
                gifRemoteKeysDao.getRemoteKeys(id = gif.id)
            }
    }


}