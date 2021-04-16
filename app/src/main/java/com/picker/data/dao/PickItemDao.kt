package com.picker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picker.data.model.PickItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PickItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pickItem: PickItem)

    @Query("SELECT * FROM PickItem")
    fun allPickItems(): Flow<List<PickItem>>
}