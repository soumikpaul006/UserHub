package com.codegalaxy.testingpracticeimpl.model.repository

import androidx.lifecycle.LiveData
import com.codegalaxy.testingpracticeimpl.model.dao.UserDao
import com.codegalaxy.testingpracticeimpl.model.entity.Comment
import com.codegalaxy.testingpracticeimpl.model.entity.User
import com.codegalaxy.testingpracticeimpl.model.network.ApiService
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) : IUserRepository {

    override fun getLocalUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

    override suspend fun getRemoteUsers(): List<User> {
        return apiService.getUsers()
    }

    override suspend fun saveUsers(users: List<User>) {
        userDao.insertUsers(users)
    }

    override suspend fun getComments(userId: Int): List<Comment> {
        return apiService.getComments(userId)
    }
}