package com.vverbytskyi.todoapp.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textview.MaterialTextView
import com.vverbytskyi.todoapp.R
import com.vverbytskyi.todoapp.domain.model.TodoDomainEntity

class TodoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textViewTodoItemContent: MaterialTextView =
        itemView.findViewById(R.id.textViewTodoItemContent)

    private val checkboxTodoItemIsCompleted: MaterialCheckBox =
        itemView.findViewById(R.id.checkboxTodoItemIsCompleted)

    fun bind(data: TodoDomainEntity) {
        textViewTodoItemContent.text = data.content
        checkboxTodoItemIsCompleted.isChecked = data.isCompleted
    }
}