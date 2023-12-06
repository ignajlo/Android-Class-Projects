package com.example.shopapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val price: Double,
    val description: String,
    val addedQuantity: Int = 0
)