package com.vverbytskyi.todoapp

import android.app.Application
import com.vverbytskyi.todoapp.di.modules.mappersModule
import com.vverbytskyi.todoapp.di.modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TodoApplication)
            modules(listOf(
                networkModule,
                mappersModule
            ))
        }
    }
}