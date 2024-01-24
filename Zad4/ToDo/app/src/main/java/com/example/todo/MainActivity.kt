package com.example.todo

import SwipeToDeleteCallback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.SharedPreferences
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var database: TaskDatabase
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        taskRecyclerView = findViewById(R.id.rv_task)
        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        database = TaskDatabase(this)
        sharedPreferences = getPreferences(MODE_PRIVATE)

        if (!sharedPreferences.getBoolean("DATABASE_INITIALIZED", false)) {
            runBlocking {
                insertInitialTasks()
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


        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        taskViewModel.name.observe(this, { nameValue ->
            findViewById<TextView>(R.id.tv_title).text = nameValue
        })

        taskViewModel.description.observe(this, { opisValue ->
            findViewById<TextView>(R.id.tv_description).text = opisValue
        })

        taskViewModel.date.observe(this, { dataValue ->
            findViewById<TextView>(R.id.tv_created_at).text = dataValue
        })

        taskViewModel.status.observe(this, { statusValue ->
            findViewById<CheckBox>(R.id.cb_done).isChecked = statusValue
        })

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


