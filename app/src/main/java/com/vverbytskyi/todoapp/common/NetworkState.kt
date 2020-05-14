package com.vverbytskyi.todoapp.common

sealed class NetworkState<out T : Any>

object StartedState : NetworkState<Nothing>()

data class ErrorState(val error: String): NetworkState<Nothing>()

data class CompletedState<out T : Any>(val data: T) : NetworkState<T>()
