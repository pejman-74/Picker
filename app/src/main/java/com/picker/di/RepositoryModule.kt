package com.picker.di

import com.picker.data.dao.PickItemDao
import com.picker.data.repository.MainRepository
import com.picker.data.repository.MainRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMainRepository(pickItemDao: PickItemDao): MainRepositoryInterface =
        MainRepository(pickItemDao)
}