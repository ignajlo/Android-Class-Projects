package com.example.todo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    companion object {
        const val DATABASE_NAME = "todo.db"
        const val DATABASE_VERSION = 1
    }

    private val CREATE_TABLE_TODO = """CREATE TABLE task (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        title TEXT,
        description TEXT,
        is_done INTEGER DEFAULT 0,
        created_at TEXT DEFAULT CURRENT_TIMESTAMP
    )"""

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_TODO)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS task")
        onCreate(db)
    }
}