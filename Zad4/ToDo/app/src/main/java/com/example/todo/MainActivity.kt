package com.example.todo

import SwipeToDeleteCallback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var database: TaskDatabase
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskRecyclerView = findViewById(R.id.rv_task)
        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        database = TaskDatabase(this)
        sharedPreferences = getPreferences(MODE_PRIVATE)

        // Check if the database has already been initialized
        if (!sharedPreferences.getBoolean("DATABASE_INITIALIZED", false)) {
            // Initialize the database only once
            runBlocking {
                insertInitialTasks()
                // Mark the database as initialized in SharedPreferences
                sharedPreferences.edit().putBoolean("DATABASE_INITIALIZED", true).apply()
            }
        }

        val taskList = database.getAll()
        taskAdapter = TaskAdapter(taskList)
        taskRecyclerView.adapter = taskAdapter

        val swipeHandler = object : SwipeToDeleteCallback(taskAdapter) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                super.onSwiped(viewHolder, direction)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(taskRecyclerView)
    }

    private suspend fun insertInitialTasks() {
        val initialTasks = listOf(
            TaskDatabase.Task(1, "task1", "description1", false, "2021-10-01 00:00:00"),
            TaskDatabase.Task(2, "task2", "description2", false, "2021-10-02 00:00:00"),
            TaskDatabase.Task(3, "task3", "description3", true, "2021-10-03 00:00:00"),
            TaskDatabase.Task(4, "task4", "description4", false, "2021-10-04 00:00:00"),
            TaskDatabase.Task(5, "task5", "description5", true, "2021-10-05 00:00:00"),
            TaskDatabase.Task(6, "task6", "description6", true, "2021-10-06 00:00:00")
        )

        for (task in initialTasks) {
            database.insert(task)
        }
    }
}


