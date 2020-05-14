package com.vverbytskyi.todoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vverbytskyi.todoapp.R
import com.vverbytskyi.todoapp.common.CompletedState
import com.vverbytskyi.todoapp.common.ErrorState
import com.vverbytskyi.todoapp.common.StartedState
import com.vverbytskyi.todoapp.domain.model.TodoDomainEntity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class TodoActivity : AppCompatActivity(), TodoAdapter.Listener {

    private val viewModel by viewModel<TodoViewModel>()

    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TodoAdapter(this, this)

        buttonAdd.setOnClickListener {
            viewModel.addTodoItem(editTextItemContent.text.toString())
        }

        recyclerViewTodoItems.apply {
            adapter = this@TodoActivity.adapter
            layoutManager = LinearLayoutManager(this@TodoActivity)
        }

        viewModel.todoListData().observe(this, Observer {
            when (it) {
                is StartedState -> showLoadingState()
                is CompletedState -> onTodoItemsReceived(it.data)
                is ErrorState -> showError(it.error)
            }
        })

        viewModel.todoItemAdded().observe(this, Observer {
            when (it) {
                is StartedState -> showLoadingState()
                is CompletedState -> onTodoItemAdded()
                is ErrorState -> showError(it.error)
            }
        })

        viewModel.fetchTodoList()
    }

    override fun onTodoItemCheckedStateChanged(todoId: Int, isCompleted: Boolean) {
        viewModel.updateTodoItemCompletedState(todoId, isCompleted)
    }

    private fun onTodoItemsReceived(items: List<TodoDomainEntity>) {
        hideLoadingState()

        adapter.setData(items)
    }

    private fun onTodoItemAdded() {
        hideLoadingState()
        editTextItemContent.text = null
    }

    private fun showError(error: String) {
        hideLoadingState()

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun showLoadingState() {
        viewLoading.visibility = View.VISIBLE
    }

    private fun hideLoadingState() {
        viewLoading.visibility = View.GONE
    }
}
