package com.vverbytskyi.todoapp.domain

import com.vverbytskyi.todoapp.common.NetworkState
import com.vverbytskyi.todoapp.domain.model.TodoDomainEntity

interface TodoListUseCase {

    suspend fun getTodoList(userId: Int): NetworkState<List<TodoDomainEntity>>

    suspend fun markTodoItem(isCompleted: Boolean): NetworkState<Boolean>
}