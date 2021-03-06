package com.picker.data.repository

import com.picker.data.model.PickItem
import kotlinx.coroutines.flow.Flow

interface MainRepositoryInterface {
    suspend fun savePickItem(pickItem: PickItem)

    fun allPickItems(): Flow<List<PickItem>>
}