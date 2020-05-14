package com.vverbytskyi.todoapp.domain.model

data class TodoDomainEntity(
    val itemId: Int,
    val userId: Int,
    val content: String,
    val isCompleted: Boolean
)