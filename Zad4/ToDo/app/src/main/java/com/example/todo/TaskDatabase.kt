package com.example.todo

import android.content.ContentValues
import android.content.Context

class TaskDatabase(context: Context) {
    private val databaseHelper = DatabaseHelper(context)
    fun insert(task: Task){
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply{
            put("title", task.title)
            put("description", task.description)
            put("is_done", task.done)
            put("created_at", task.createdAt)
        }
        db.insert("task", null, values)
        db.close()
    }
    fun count(): Int{
        val db = databaseHelper.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM task", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        db.close()
        return count
    }

    fun getAll(): List<Task>{
        val db = databaseHelper.readableDatabase
        val cursor = db.query("task", null, null, null, null, null, null)
        val tasks = mutableListOf<Task>()
        with(cursor){
            while (moveToNext()) {
                val idIndex = getColumnIndex("id")
                val titleIndex = getColumnIndex("title")
                val descriptionIndex = getColumnIndex("description")
                val doneIndex = getColumnIndex("is_done")
                val createdAtIndex = getColumnIndex("created_at")

                // Check if getColumnIndex returns -1
                if (idIndex != -1 && titleIndex != -1 && descriptionIndex != -1 && doneIndex != -1 && createdAtIndex != -1) {
                    val id = getInt(idIndex)
                    val title = getString(titleIndex)
                    val description = getString(descriptionIndex)
                    val done = getInt(doneIndex) == 1
                    val createdAt = getString(createdAtIndex)
                    tasks.add(Task(id, title, description, done, createdAt))
                }
            }
        }
        db.close()
        return tasks
    }

    fun update(task: Task){
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply{
            put("title", task.title)
            put("description", task.description)
            put("is_done", task.done)
            put("created_at", task.createdAt)
        }
        db.update("task", values, "id = ?", arrayOf(task.id.toString()))
        db.close()
    }

    fun delete(task: Task){
        val db = databaseHelper.writableDatabase
        db.delete("task", "id = ?", arrayOf(task.id.toString()))
        db.close()
    }
    data class Task(
        val id: Int,
        val title: String,
        val description: String,
        val done: Boolean,
        val createdAt: String
    )
}