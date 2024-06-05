package com.rakhlinskyi.natifeapp.core.data.remote.dto

import androidx.room.Embedded
import com.squareup.moshi.Json

data class GifOptionsDto(
    @Json(name = "original")
    @Embedded(prefix = "original_") val original: GifOptionInfoDto,
    @Json(name = "fixed_height")
    @Embedded(prefix = "fixed_height_") val fixed_height: GifOptionInfoDto,
)