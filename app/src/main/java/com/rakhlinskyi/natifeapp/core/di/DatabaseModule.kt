package com.rakhlinskyi.natifeapp.core.di

import android.content.Context
import androidx.room.Room
import com.rakhlinskyi.natifeapp.core.data.local.GifsDatabase
import com.rakhlinskyi.natifeapp.core.utils.Constants.GIPHY_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): GifsDatabase {
        return Room.databaseBuilder(
            context,
            GifsDatabase::class.java,
            GIPHY_DATABASE
        ).build()
    }

}