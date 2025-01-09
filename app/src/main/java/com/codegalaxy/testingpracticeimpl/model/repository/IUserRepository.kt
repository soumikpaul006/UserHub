package com.codegalaxy.testingpracticeimpl.model.repository

import androidx.lifecycle.LiveData
import com.codegalaxy.testingpracticeimpl.model.entity.Comment
import com.codegalaxy.testingpracticeimpl.model.entity.User

interface IUserRepository {
    fun getLocalUsers(): LiveData<List<User>>
    suspend fun getRemoteUsers(): List<User>
    suspend fun saveUsers(users: List<User>)

    suspend fun getComments(userId: Int): List<Comment>

}