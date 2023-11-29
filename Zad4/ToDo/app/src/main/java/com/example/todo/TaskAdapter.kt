package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private var taskList: List<TaskDatabase.Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun removeTask(position: Int) {
        val updatedList = taskList.toMutableList()
        updatedList.removeAt(position)
        taskList = updatedList
        notifyItemRemoved(position)
    }

    fun updateData(tasks: List<TaskDatabase.Task>?) {
        tasks?.let {
            taskList = it
            notifyDataSetChanged()
        }
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: TaskDatabase.Task) {
            itemView.findViewById<TextView>(R.id.tv_title).text = task.title
            itemView.findViewById<TextView>(R.id.tv_description).text = task.description
            itemView.findViewById<TextView>(R.id.tv_created_at).text = task.createdAt
            itemView.findViewById<CheckBox>(R.id.cb_done).isChecked = task.done
        }
    }
}