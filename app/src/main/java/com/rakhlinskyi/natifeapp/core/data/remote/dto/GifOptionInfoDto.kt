package com.rakhlinskyi.natifeapp.core.data.remote.dto

import com.squareup.moshi.Json

data class GifOptionInfoDto(
    @Json(name = "url")
    val url: String,
    @Json(name = "width")
    val width: String,
    @Json(name = "height")
    val height: String,
    @Json(name = "size")
    val size: String,
    @Json(name = "images")
    val mp4_size: String?,
    @Json(name = "mp4")
    val mp4: String?,
    @Json(name = "webp_size")
    val webp_size: String?,
    @Json(name = "webp")
    val webp: String?,
    @Json(name = "hash")
    val hash: String?,
    @Json(name = "frames")
    val frames: String?
)
