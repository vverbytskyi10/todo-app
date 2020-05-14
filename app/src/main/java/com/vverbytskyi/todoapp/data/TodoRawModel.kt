package com.vverbytskyi.todoapp.data

data class TodoRawModel(
    val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)