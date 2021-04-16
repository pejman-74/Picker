package com.picker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picker.data.dao.PickItemDao
import com.picker.data.model.PickItem

@Database(entities = [PickItem::class], version = 1)
abstract class PickDatabase : RoomDatabase() {
    abstract fun picItemDao(): PickItemDao
}