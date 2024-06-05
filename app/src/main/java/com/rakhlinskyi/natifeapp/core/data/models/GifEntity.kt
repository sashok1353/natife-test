package com.rakhlinskyi.natifeapp.core.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rakhlinskyi.natifeapp.core.utils.Constants.GIFS_TABLE
import com.squareup.moshi.Json

@Entity(tableName = GIFS_TABLE)
data class GifEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val type: String,
    val title: String,
    val rating: String,
    @Json(name = "images")
    @Embedded val gifOptions: GifOptions
)