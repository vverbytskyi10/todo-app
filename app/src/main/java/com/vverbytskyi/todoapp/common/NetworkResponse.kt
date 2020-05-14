package com.vverbytskyi.todoapp.common

sealed class NetworkResponse<out T : Any>

data class SuccessResponse<out T : Any>(val data: T): NetworkResponse<T>()

data class FailureResponse(val throwable: Throwable?): NetworkResponse<Nothing>()