package com.sameerasw.roomdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameerasw.roomdb.db.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date
import java.time.Instant

class TodoVM: ViewModel() {
    val todoDAO = MainApplication.database.getTodoDAO()
    val todoItems: LiveData<List<Todo>> = todoDAO.getAllTodoItems()

    fun addTodoItem(title: String) {
        // this runs the db instance in a separate thread to prevent crashing the main thread
        viewModelScope.launch(Dispatchers.IO) {
            val item = Todo(
                title = title,
                createdAt = Date.from(Instant.now())
            )
            todoDAO.addTodoItem(item)
        }
    }

    fun deleteTodoItem(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDAO.deleteTodoItem(id)
        }
    }
}