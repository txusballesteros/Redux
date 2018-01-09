package com.txusballesteros.redux.todolist.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.github.salomonbrys.kodein.instance
import com.txusballesteros.redux.core.state.TodoListState
import com.txusballesteros.redux.coreview.view.AbsFragment
import com.txusballesteros.redux.todolist.R
import com.txusballesteros.redux.todolist.di.featureComponent
import com.txusballesteros.redux.todolist.presentation.TodoListPresenter
import kotlinx.android.synthetic.main.fragment_todo_list.*

class TodoListFragment: AbsFragment(), TodoListPresenter.View {
    companion object {
        fun newInstance() = TodoListFragment()
    }

    private val presenter: TodoListPresenter = featureComponent.instance()
    private val adapter: TodoListAdapter = TodoListAdapter()

    override fun onRequestLayoutResourceId(): Int = R.layout.fragment_todo_list

    override fun onViewReady(savedInstanceState: Bundle?) {
        prepareList()
        presenter.onViewReady(this)
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
        adapter.addAll(state)
        adapter.notifyDataSetChanged()
    }
}