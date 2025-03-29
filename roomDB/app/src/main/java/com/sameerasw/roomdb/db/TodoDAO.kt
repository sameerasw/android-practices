package com.sameerasw.roomdb.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDAO {
    @Query("SELECT * FROM Todo ORDER BY createdAt DESC")
    fun getAllTodoItems(): LiveData<List<Todo>> // make sure it's the same Todo object or otherwise it will not work.

    @Insert
    fun addTodoItem(todo: Todo)

    @Query("DELETE FROM Todo WHERE id = :id")
    fun deleteTodoItem(id: Int) // the id declared above should be the same or otherwise it will fail

    @Query("UPDATE Todo SET isDone = :isDone WHERE id = :id")
    fun updateTodoItem(id: Int, isDone: Boolean)
}