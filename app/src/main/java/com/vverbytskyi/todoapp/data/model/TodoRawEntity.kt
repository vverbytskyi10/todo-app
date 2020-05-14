package com.vverbytskyi.todoapp.data.model

data class TodoRawEntity(
    val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)