package com.rakhlinskyi.natifeapp.core.domain.models

data class GifModel(
    val id: String,
    val type: String,
    val title: String,
    val rating: String,
    val gifOptions: GifOptionsModel
)
