package com.txusballesteros.redux.todolist.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.salomonbrys.kodein.instance
import com.txusballesteros.redux.core.state.TodoListState
import com.txusballesteros.redux.coreview.view.AbsFragment
import com.txusballesteros.redux.todolist.R
import com.txusballesteros.redux.todolist.di.featureComponent
import com.txusballesteros.redux.todolist.presentation.TodoListPresenter
import kotlinx.android.synthetic.main.dialog_add_todo.view.*
import kotlinx.android.synthetic.main.fragment_todo_list.*
import org.jetbrains.anko.support.v4.alert

class TodoListFragment: AbsFragment(), TodoListPresenter.View {
    companion object {
        fun newInstance() = TodoListFragment()
    }

    private val presenter: TodoListPresenter = featureComponent.instance()
    private val adapter: TodoListAdapter = TodoListAdapter()

    override fun onRequestLayoutResourceId(): Int = R.layout.fragment_todo_list

    override fun onViewReady(savedInstanceState: Bundle?) {
        prepareList()
        prepareListeners()
        presenter.onViewReady(this)
    }

    private fun prepareListeners() {
        addTodoView.setOnClickListener { presenter.onAddTodoTap() }
    }

    private fun prepareList() {
        listView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDestroyed()
    }

    override fun render(state: TodoListState) {
        adapter.clear()
        adapter.addAll(state)
        adapter.notifyDataSetChanged()
    }

    override fun showAddToDoDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_todo, view as ViewGroup, false)
        alert {
            title = getString(R.string.add_todo_title)
            isCancelable = false
            customView = dialogView
            negativeButton(buttonTextResource = R.string.add_todo_cancel_button, onClicked = { })
            positiveButton(buttonTextResource = R.string.add_todo_accept_button, onClicked = {
                with(dialogView) {
                    val todoText = textView.text.toString()
                    presenter.addTodo(todoText)
                }
            })
        }.show()
    }
}