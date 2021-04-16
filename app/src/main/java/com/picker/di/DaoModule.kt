package com.picker.di

import com.picker.data.dao.PickItemDao
import com.picker.data.database.PickDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Singleton
    @Provides
    fun providePickItemDao(pickDatabase: PickDatabase): PickItemDao = pickDatabase.picItemDao()
}