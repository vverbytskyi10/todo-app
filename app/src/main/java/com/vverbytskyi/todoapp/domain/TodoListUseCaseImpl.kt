package com.vverbytskyi.todoapp.domain

import com.vverbytskyi.todoapp.common.*
import com.vverbytskyi.todoapp.domain.model.TodoDomainEntity

class TodoListUseCaseImpl(
    private val todoListRepository: TodoListRepository
) : TodoListUseCase {

    override suspend fun getTodoList(userId: Int): NetworkState<List<TodoDomainEntity>> {
        return when (val response = todoListRepository.getTodoList(userId)) {
            is SuccessResponse -> CompletedState(response.data)
            is FailureResponse -> ErrorState("")
        }
    }

    override suspend fun markTodoItem(isCompleted: Boolean): NetworkState<Boolean> {
        return CompletedState(isCompleted)
    }

    override suspend fun addTodoItem(userId: Int, content: String): NetworkState<TodoDomainEntity> {
        return CompletedState(TodoDomainEntity(100, userId, content, false))
    }
}