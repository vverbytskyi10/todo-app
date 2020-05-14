package com.vverbytskyi.todoapp.mapper

import com.vverbytskyi.todoapp.data.model.TodoRawEntity
import com.vverbytskyi.todoapp.domain.model.TodoDomainEntity

class TodoEntityMapper {

    fun rawToDomain(entity: TodoRawEntity): TodoDomainEntity {
        return TodoDomainEntity(entity.id, entity.userId, entity.title, entity.completed)
    }
}