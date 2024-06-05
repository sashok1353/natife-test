package com.rakhlinskyi.natifeapp.core.data.mappers

import com.rakhlinskyi.natifeapp.core.data.models.GifEntity
import com.rakhlinskyi.natifeapp.core.data.models.GifOptionInfo
import com.rakhlinskyi.natifeapp.core.data.models.GifOptions
import com.rakhlinskyi.natifeapp.core.data.remote.dto.GifDto
import com.rakhlinskyi.natifeapp.core.data.remote.dto.GifOptionInfoDto
import com.rakhlinskyi.natifeapp.core.data.remote.dto.GifOptionsDto
import com.rakhlinskyi.natifeapp.core.domain.models.GifModel
import com.rakhlinskyi.natifeapp.core.domain.models.GifOptionInfoModel
import com.rakhlinskyi.natifeapp.core.domain.models.GifOptionsModel

//fun GiphyResponseDto.toGiphyResponseEntity() : GiphyResponse {
//    return GifResponseEntity(
//        data = data.map { it.toGifEntity() },
//    )
//}

fun GifDto.toGifEntity(): GifEntity {
    return GifEntity(
        id = id,
        type = type,
        title = title,
        rating = rating,
        gifOptions = gifOptions.toGifOptions()
    )
}

fun GifOptionsDto.toGifOptions(): GifOptions {
    return GifOptions(
        original = original.toGifOptionInfo(),
        fixedHeight = fixed_height.toGifOptionInfo()
    )
}

fun GifOptionInfoDto.toGifOptionInfo(): GifOptionInfo {
    return GifOptionInfo(
        url = url,
        width = width,
        height = height,
        size = size,
        mp4Size = mp4_size,
        mp4 = mp4,
        webpSize = webp_size,
        webp = webp,
        hash = hash,
        frames = frames
    )
}

fun GifEntity.toGif(): GifModel {
    return GifModel(
        id = id,
        type = type,
        title = title,
        rating = rating,
        gifOptions = gifOptions.toGifOptionsModel()
    )
}

fun GifOptions.toGifOptionsModel(): GifOptionsModel {
    return GifOptionsModel(
        original = original.toGifOptionInfoModel(),
        fixedHeight = fixedHeight.toGifOptionInfoModel()
    )
}

fun GifOptionInfo.toGifOptionInfoModel(): GifOptionInfoModel {
    return GifOptionInfoModel(
        url = url,
        width = width,
        height = height,
        size = size,
        mp4Size = mp4Size,
        mp4 = mp4,
        webpSize = webpSize,
        webp = webp,
        hash = hash,
        frames = frames
    )
}