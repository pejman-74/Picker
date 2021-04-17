package com.picker.data.repository

import com.picker.data.model.PickItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMainRepository : MainRepositoryInterface {

    private val pickItemDB = MutableStateFlow<List<PickItem>>(emptyList())

    override suspend fun savePickItem(pickItem: PickItem) {
        pickItemDB.value += pickItem
    }

    override fun allPickItems(): Flow<List<PickItem>> = pickItemDB

}
