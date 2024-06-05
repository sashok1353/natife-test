package com.rakhlinskyi.natifeapp.core.data.remote

import com.rakhlinskyi.natifeapp.BuildConfig
import com.rakhlinskyi.natifeapp.core.data.remote.dto.GiphyResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {

    @GET("gifs/trending")
    suspend fun getAllGifs(
        @Query("offset") page: Int,
        @Query("limit") perPage: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): GiphyResponseDto

    @GET("gifs/search")
    suspend fun searchGifs(
        @Query("q") query: String,
        @Query("limit") perPage: Int,
        @Query("offset") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): GiphyResponseDto
}