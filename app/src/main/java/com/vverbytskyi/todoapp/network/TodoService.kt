package com.vverbytskyi.todoapp.network

import com.vverbytskyi.todoapp.data.model.TodoRawEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoService {

    @GET("/todos")
    suspend fun getTodoList(@Query("userId") userId: Int): Response<List<TodoRawEntity>>
}