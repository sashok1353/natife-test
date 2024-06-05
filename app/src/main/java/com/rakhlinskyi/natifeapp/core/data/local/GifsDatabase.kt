package com.rakhlinskyi.natifeapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rakhlinskyi.natifeapp.core.data.local.dao.GifDao
import com.rakhlinskyi.natifeapp.core.data.local.dao.GifRemoteKeysDao
import com.rakhlinskyi.natifeapp.core.data.models.GifEntity
import com.rakhlinskyi.natifeapp.core.data.models.GifRemoteKeysEntity

@Database(
    entities = [GifEntity::class, GifRemoteKeysEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GifsDatabase : RoomDatabase() {

    abstract fun gifsDao(): GifDao
    abstract fun gifsRemoteKeysDao(): GifRemoteKeysDao

}