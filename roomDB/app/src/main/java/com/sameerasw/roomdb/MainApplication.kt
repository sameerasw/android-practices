package com.sameerasw.roomdb

import android.app.Application
import androidx.room.Room
import com.sameerasw.roomdb.db.TodoDatabase

class MainApplication: Application() {
    companion object{
        lateinit var database: TodoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java, // defining the interface
            TodoDatabase.DATABASE_NAME
        ).build()
    }
}