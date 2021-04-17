package com.picker.data.repository

import com.picker.data.dao.PickItemDao
import com.picker.data.model.PickItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class MainRepository(
    private val pickItemDao: PickItemDao,
    private val ioDispatcher: CoroutineDispatcher
) : MainRepositoryInterface {


    override suspend fun savePickItem(pickItem: PickItem) =
        withContext(ioDispatcher) { pickItemDao.save(pickItem) }

    override fun allPickItems(): Flow<List<PickItem>> =
        pickItemDao.allPickItems().flowOn(ioDispatcher)

}