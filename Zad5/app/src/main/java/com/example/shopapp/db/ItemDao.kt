package com.example.shopapp.db

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import androidx.room.Insert

@Dao
interface ItemDao {
    @Query("SELECT * FROM item_table")
    fun getAll(): Flow<List<Item>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item): Long
    @Query("DELETE FROM item_table WHERE id = :id")
    fun delete(id: Long)
    @Query("SELECT * FROM item_table WHERE addedQuantity > 0")
    fun getItemsInCart(): Flow<List<Item>>
    @Query("UPDATE item_table SET addedQuantity = addedQuantity + :amount WHERE id = :itemId")
    fun increaseItemQuantity(itemId: Long, amount: Int)

    @Query("UPDATE item_table SET addedQuantity = :amount WHERE id = :itemId")
    fun setItemQuantity(itemId: Long, amount: Int)
    @Query("UPDATE item_table SET addedQuantity = addedQuantity - :amount WHERE id = :itemId")
    fun decreaseItemQuantity(itemId: Long, amount: Int)
    @Query("SELECT * FROM item_table WHERE id = :itemId")
    fun getItem(itemId: Long): Item
}