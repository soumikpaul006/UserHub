package com.codegalaxy.testingpracticeimpl.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codegalaxy.testingpracticeimpl.model.dao.UserDao
import com.codegalaxy.testingpracticeimpl.model.entity.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}