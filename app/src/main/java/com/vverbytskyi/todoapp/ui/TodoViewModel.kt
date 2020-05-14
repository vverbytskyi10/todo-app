package com.vverbytskyi.todoapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vverbytskyi.todoapp.common.CompletedState
import com.vverbytskyi.todoapp.common.ErrorState
import com.vverbytskyi.todoapp.common.NetworkState
import com.vverbytskyi.todoapp.common.StartedState
import com.vverbytskyi.todoapp.domain.TodoListUseCase
import com.vverbytskyi.todoapp.domain.model.TodoDomainEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val USER_ID = 1

private const val INDEX_NOT_FOUND = -1

class TodoViewModel(private val todoListUseCase: TodoListUseCase) : ViewModel() {

    private val todoListData = MutableLiveData<NetworkState<List<TodoDomainEntity>>>()
    private val todoItemAdded = MutableLiveData<NetworkState<TodoDomainEntity>>()

    fun fetchTodoList() {
        todoListData.value = StartedState

        viewModelScope.launch {
            todoListData.value = withContext(Dispatchers.IO) {
                todoListUseCase.getTodoList(USER_ID)
            }
        }
    }

    fun updateTodoItemCompletedState(itemId: Int, isCompleted: Boolean) {
        var originalList = (todoListData.value as? CompletedState)?.data

        if (originalList != null) {
            val index = originalList.indexOfFirst { it.itemId == itemId }

            if (itemId != INDEX_NOT_FOUND) {
                val item = originalList[index]

                originalList = originalList.minus(item).toMutableList()

                originalList.add(index, item.copy(isCompleted = isCompleted))

                todoListData.value = CompletedState(originalList)
            }
        }
    }

    fun addTodoItem(content: String) {
        if (content.isEmpty()) {
            todoItemAdded.value = ErrorState("item can't be empty")
        } else {
            todoItemAdded.value = StartedState

            viewModelScope.launch {
                val state = withContext(Dispatchers.IO) {
                    todoListUseCase.addTodoItem(USER_ID, content)
                }

                when (state) {
                    is CompletedState -> {
                        val list = mutableListOf<TodoDomainEntity>()

                        (todoListData.value as? CompletedState)?.data?.also {
                            list.addAll(it)
                        }

                        list.add(0, state.data)

                        todoListData.value = CompletedState(list)
                        todoItemAdded.value = CompletedState(state.data)
                    }
                    else -> state
                }
            }
        }
    }

    fun todoListData(): LiveData<NetworkState<List<TodoDomainEntity>>> {
        return todoListData
    }

    fun todoItemAdded(): LiveData<NetworkState<TodoDomainEntity>> {
        return todoItemAdded
    }
}