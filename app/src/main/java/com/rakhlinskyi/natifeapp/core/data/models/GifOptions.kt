package com.rakhlinskyi.natifeapp.core.data.models

import androidx.room.Embedded

data class GifOptions(
    @Embedded(prefix = "original_") val original: GifOptionInfo,
    @Embedded(prefix = "fixed_height_") val fixedHeight: GifOptionInfo,
)