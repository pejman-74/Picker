package com.picker.di

import android.content.Context
import androidx.room.Room
import com.picker.data.database.PickDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModuleTest {
    @Provides
    @Singleton
    fun providePickDatabase(@ApplicationContext context: Context): PickDatabase =
        Room.inMemoryDatabaseBuilder(context, PickDatabase::class.java).allowMainThreadQueries()
            .build()
}