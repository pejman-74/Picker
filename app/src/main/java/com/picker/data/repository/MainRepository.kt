package com.picker.data.repository

import com.picker.data.dao.PickItemDao
import com.picker.data.model.PickItem
import kotlinx.coroutines.flow.Flow

class MainRepository(private val pickItemDao: PickItemDao) : MainRepositoryInterface {


    override suspend fun savePickItem(pickItem: PickItem) = pickItemDao.save(pickItem)

    override suspend fun allPickItems(): Flow<List<PickItem>> = pickItemDao.allPickItems()

}