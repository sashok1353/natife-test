package com.rakhlinskyi.natifeapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rakhlinskyi.natifeapp.core.data.models.GifRemoteKeysEntity

@Dao
interface GifRemoteKeysDao {

    @Query("SELECT * FROM gifs_remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: String): GifRemoteKeysEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<GifRemoteKeysEntity>)

    @Query("DELETE FROM gifs_remote_keys_table")
    suspend fun deleteAllRemoteKeys()

}