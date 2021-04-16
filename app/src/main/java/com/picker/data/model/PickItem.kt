package com.picker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PickItem(
    val title: String,
    val description: String,
    val crateTime: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
