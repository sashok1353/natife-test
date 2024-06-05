package com.rakhlinskyi.natifeapp.core.domain.models

data class GifOptionInfoModel(
    val url: String,
    val width: String,
    val height: String,
    val size: String,
    val mp4Size: String?,
    val mp4: String?,
    val webpSize: String?,
    val webp: String?,
    val hash: String?,
    val frames: String?
)