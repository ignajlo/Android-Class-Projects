package com.example.shopapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 2 )
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}
suspend fun fillDatabase(itemDao: ItemDao){
    val items = listOf(
        Item(1,"Coffee", 10.5, "Black coffee",  0),
        Item(2,"Tea", 5.0, "Black tea",  0),
        Item(3,"Milk", 2.0, "Carton of milk",  10),
        Item(4,"Sugar", 1.0, "1k of sugar",  0),
        Item(5,"Salt", 11.32, "1kg of salt",  1),
        Item(6,"Gold", 11234567.0, "1kg of gold",  0),
    )
    items.forEach { itemDao.insert(it) }
}