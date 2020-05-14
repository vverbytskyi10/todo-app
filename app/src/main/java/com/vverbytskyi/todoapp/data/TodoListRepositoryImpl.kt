package com.vverbytskyi.todoapp.data

import com.vverbytskyi.todoapp.common.FailureResponse
import com.vverbytskyi.todoapp.common.NetworkResponse
import com.vverbytskyi.todoapp.common.SuccessResponse
import com.vverbytskyi.todoapp.domain.TodoListRepository
import com.vverbytskyi.todoapp.domain.model.TodoDomainEntity
import com.vverbytskyi.todoapp.mapper.TodoEntityMapper
import com.vverbytskyi.todoapp.network.TodoService

class TodoListRepositoryImpl(
    private val todoService: TodoService,
    private val todoEntityMapper: TodoEntityMapper
) : TodoListRepository {

    override suspend fun getTodoList(userId: Int): NetworkResponse<List<TodoDomainEntity>> {
        return try {
            val response = todoService.getTodoList(userId)
            val responseData = response.body()

            when {
                response.isSuccessful && responseData != null -> SuccessResponse(
                    responseData.map { todoEntityMapper.rawToDomain(it) })
                else -> FailureResponse(Throwable())
            }
        } catch (e: Throwable) {
            FailureResponse(e)
        }

    }

}