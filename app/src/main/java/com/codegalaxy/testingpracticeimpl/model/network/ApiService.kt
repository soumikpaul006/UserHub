package com.codegalaxy.testingpracticeimpl.model.network

import com.codegalaxy.testingpracticeimpl.model.entity.Comment
import com.codegalaxy.testingpracticeimpl.model.entity.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("comments")
    suspend fun getComments(
        @Query("postId") postId: Int
    ): List<Comment>
}