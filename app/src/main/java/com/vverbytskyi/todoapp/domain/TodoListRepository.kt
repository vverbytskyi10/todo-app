package com.vverbytskyi.todoapp.domain

import com.vverbytskyi.todoapp.common.NetworkResponse
import com.vverbytskyi.todoapp.domain.model.TodoDomainEntity

interface TodoListRepository {

    suspend fun getTodoList(userId: Int): NetworkResponse<List<TodoDomainEntity>>
}