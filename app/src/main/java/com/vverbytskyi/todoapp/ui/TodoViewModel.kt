package com.vverbytskyi.todoapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vverbytskyi.todoapp.common.NetworkState
import com.vverbytskyi.todoapp.common.StartedState
import com.vverbytskyi.todoapp.domain.TodoListUseCase
import com.vverbytskyi.todoapp.domain.model.TodoDomainEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val USER_ID = 1

class TodoViewModel(private val todoListUseCase: TodoListUseCase) : ViewModel() {

    private val todoListData = MutableLiveData<NetworkState<List<TodoDomainEntity>>>()

    fun fetchTodoList() {
        todoListData.value = StartedState

        viewModelScope.launch {
            todoListData.value = withContext(Dispatchers.IO) {
                todoListUseCase.getTodoList(USER_ID)
            }
        }
    }

    fun todoListData(): LiveData<NetworkState<List<TodoDomainEntity>>> {
        return todoListData
    }
}