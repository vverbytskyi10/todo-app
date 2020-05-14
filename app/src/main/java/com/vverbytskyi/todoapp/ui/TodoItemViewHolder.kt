package com.vverbytskyi.todoapp.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textview.MaterialTextView
import com.vverbytskyi.todoapp.R
import com.vverbytskyi.todoapp.domain.model.TodoDomainEntity

class TodoItemViewHolder(itemView: View, private val listener: Listener? = null) :
    RecyclerView.ViewHolder(itemView) {

    private val rootTodoItem: View = itemView.findViewById(R.id.rootTodoItem)

    private val textViewTodoItemContent: MaterialTextView =
        itemView.findViewById(R.id.textViewTodoItemContent)

    private val checkboxTodoItemIsCompleted: MaterialCheckBox =
        itemView.findViewById(R.id.checkboxTodoItemIsCompleted)

    init {
        checkboxTodoItemIsCompleted.setOnCheckedChangeListener { button, isChecked ->
            if (button.isPressed) {
                listener?.onCheckboxStateChanged(adapterPosition, isChecked)
            }
        }
    }

    fun bind(data: TodoDomainEntity) {
        rootTodoItem.setBackgroundResource(getBackgroundColor(data.isCompleted))

        textViewTodoItemContent.text = data.content
        checkboxTodoItemIsCompleted.isChecked = data.isCompleted
    }

    private fun getBackgroundColor(isCompleted: Boolean): Int {
        return if (isCompleted) R.color.colorTodoItemCompleted else R.color.colorTodoItemNotCompleted
    }

    interface Listener {
        fun onCheckboxStateChanged(position: Int, isChecked: Boolean)
    }
}