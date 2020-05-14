package com.vverbytskyi.todoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vverbytskyi.todoapp.R
import com.vverbytskyi.todoapp.common.CompletedState
import com.vverbytskyi.todoapp.common.ErrorState
import com.vverbytskyi.todoapp.common.StartedState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class TodoActivity : AppCompatActivity() {

    private val viewModel by viewModel<TodoViewModel>()

    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TodoAdapter(this)

        recyclerViewTodoItems.apply {
            adapter = this@TodoActivity.adapter
            layoutManager = LinearLayoutManager(this@TodoActivity)
        }

        viewModel.todoListData().observe(this, Observer {
            when (it) {
                is StartedState -> Log.d("", "started")
                is CompletedState -> adapter.setData(it.data)
                is ErrorState -> Log.d("", "error")
            }
        })

        viewModel.fetchTodoList()
    }
}
