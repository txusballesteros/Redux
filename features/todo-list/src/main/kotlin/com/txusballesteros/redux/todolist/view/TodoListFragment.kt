/*
 * Copyright Txus Ballesteros 2018 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
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