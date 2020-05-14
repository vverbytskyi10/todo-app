package com.vverbytskyi.todoapp.di.modules

import com.vverbytskyi.todoapp.data.TodoListRepositoryImpl
import com.vverbytskyi.todoapp.domain.TodoListRepository
import com.vverbytskyi.todoapp.domain.TodoListUseCase
import com.vverbytskyi.todoapp.domain.TodoListUseCaseImpl
import com.vverbytskyi.todoapp.mapper.TodoEntityMapper
import com.vverbytskyi.todoapp.network.TodoService
import com.vverbytskyi.todoapp.ui.TodoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val todoModule = module {
    factory { getTodoListRepository(get(), get()) }
    factory { getTodoListUseCase(get()) }

    viewModel { TodoViewModel(get()) }
}

fun getTodoListRepository(
    todoService: TodoService,
    todoEntityMapper: TodoEntityMapper
): TodoListRepository {
    return TodoListRepositoryImpl(todoService, todoEntityMapper)
}

fun getTodoListUseCase(todoListRepository: TodoListRepository): TodoListUseCase {
    return TodoListUseCaseImpl(todoListRepository)
}