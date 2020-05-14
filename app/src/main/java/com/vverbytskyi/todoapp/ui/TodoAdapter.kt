package com.vverbytskyi.todoapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vverbytskyi.todoapp.R
import com.vverbytskyi.todoapp.domain.model.TodoDomainEntity

class TodoAdapter(
    context: Context,
    private val listener: Listener? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), TodoItemViewHolder.Listener {

    private val inflater = LayoutInflater.from(context)
    private val todoList = mutableListOf<TodoDomainEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TodoItemViewHolder(inflater.inflate(R.layout.item_todo, parent, false), this)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TodoItemViewHolder -> holder.bind(todoList[position])
        }
    }

    fun setData(data: List<TodoDomainEntity>) {
        todoList.apply {
            clear()
            addAll(data)
        }

        notifyDataSetChanged()
    }

    interface Listener {
        fun onTodoItemCheckedStateChanged(todoId: Int, isCompleted: Boolean)
    }

    override fun onCheckboxStateChanged(position: Int, isChecked: Boolean) {
        listener?.onTodoItemCheckedStateChanged(todoList[position].itemId, isChecked)
    }
}