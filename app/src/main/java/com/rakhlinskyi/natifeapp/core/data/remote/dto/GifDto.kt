package com.rakhlinskyi.natifeapp.core.data.remote.dto

import com.squareup.moshi.Json

data class GifDto(
    @Json(name = "type")
    val type: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "rating")
    val rating: String,
    @Json(name = "images")
    val gifOptions: GifOptionsDto
)