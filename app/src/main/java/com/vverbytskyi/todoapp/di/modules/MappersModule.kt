package com.vverbytskyi.todoapp.di.modules

import com.vverbytskyi.todoapp.mapper.TodoEntityMapper
import org.koin.dsl.module

val mappersModule = module {
    factory { TodoEntityMapper() }
}