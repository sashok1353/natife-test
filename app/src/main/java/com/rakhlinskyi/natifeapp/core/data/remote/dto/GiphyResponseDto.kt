package com.rakhlinskyi.natifeapp.core.data.remote.dto

import com.squareup.moshi.Json

data class GiphyResponseDto(
    @Json(name = "data")
    val data: List<GifDto>,
)