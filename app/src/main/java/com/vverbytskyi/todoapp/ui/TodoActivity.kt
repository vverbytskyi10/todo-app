package com.vverbytskyi.todoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vverbytskyi.todoapp.R
import com.vverbytskyi.todoapp.network.TodoService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import kotlin.coroutines.CoroutineContext

class TodoActivity : AppCompatActivity(), CoroutineScope {

    val todoService: TodoService = get()

    val job = Job()
    val scope = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch(Dispatchers.IO) {
            Log.d("MA", todoService.getTodoList(1).body().orEmpty().toString())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = scope
}
