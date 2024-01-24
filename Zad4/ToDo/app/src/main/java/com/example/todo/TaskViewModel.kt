package com.example.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    // Define LiveData for each field
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    private val _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() = _status

    // Method to update LiveData values
    fun updateTaskDetails(name: String, description: String, date: String, status: Boolean) {
        _name.value = name
        _description.value = description
        _date.value = date
        _status.value = status
    }
}
