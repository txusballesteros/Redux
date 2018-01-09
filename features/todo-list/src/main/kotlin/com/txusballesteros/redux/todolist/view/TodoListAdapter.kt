package com.txusballesteros.redux.todolist.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.txusballesteros.redux.core.state.TodoListState
import com.txusballesteros.redux.core.state.TodoState
import com.txusballesteros.redux.todolist.R
import kotlinx.android.synthetic.main.item_todo_list.view.*

class TodoListAdapter: RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {
    private val cache: MutableList<TodoState> = mutableListOf()

    fun clear() {
        cache.clear()
    }

    fun addAll(state: TodoListState) = cache.addAll(state.list)

    override fun getItemCount() = cache.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.itemView) {
        val state = cache[position]
        textView.text = state.text
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}