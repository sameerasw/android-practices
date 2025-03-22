package com.sameerasw.roomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Todo::class], version = 1)
@TypeConverters(Converter::class) // optional unless you got converters
abstract class TodoDatabase: RoomDatabase() {
    companion object {
        // this is a singleton by default and we use it instead of the static keyword which is not supported in kt.
        const val DATABASE_NAME = "todo_database"
    }

    abstract fun getTodoDAO(): TodoDAO

}