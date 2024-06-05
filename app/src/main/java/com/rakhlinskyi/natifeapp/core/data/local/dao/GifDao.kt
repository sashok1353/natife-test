package com.rakhlinskyi.natifeapp.core.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rakhlinskyi.natifeapp.core.data.models.GifEntity

@Dao
interface GifDao {

    @Query("SELECT * FROM gifs_table")
    fun getAllGifs(): PagingSource<Int, GifEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGifs(gifs: List<GifEntity>)

    @Query("DELETE FROM gifs_table")
    suspend fun deleteAllGifs()

    @Query("DELETE FROM gifs_table WHERE id = :gifId")
    suspend fun deleteGifById(gifId: String)
}