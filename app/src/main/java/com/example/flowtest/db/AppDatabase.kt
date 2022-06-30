package com.example.flowtest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Config::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun configDao(): ConfigDao

    companion object {
        fun create(applicationContext: Context) = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }
}
