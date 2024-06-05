package com.rakhlinskyi.natifeapp.core.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rakhlinskyi.natifeapp.core.utils.Constants.GIFS_REMOTE_KEYS_TABLE

@Entity(tableName = GIFS_REMOTE_KEYS_TABLE)
data class GifRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)