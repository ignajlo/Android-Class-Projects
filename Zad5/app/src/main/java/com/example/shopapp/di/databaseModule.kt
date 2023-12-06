package com.example.shopapp.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.shopapp.db.AppDatabase
import com.example.shopapp.db.fillDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "app_db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db : SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val appDatabase = get<AppDatabase>()
                    val productDao = appDatabase.itemDao()
                    CoroutineScope(Dispatchers.IO).launch {
                        fillDatabase(productDao)
                    }
                }
            })
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().itemDao() }

}