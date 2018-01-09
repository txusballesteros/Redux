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
package com.txusballesteros.redux.todolist.presentation

import com.txusballesteros.redux.core.action.AddTodoAction
import com.txusballesteros.redux.core.state.TodoListState
import com.txusballesteros.redux.core.store.TodoListStore
import com.txusballesteros.redux.coreview.presentation.Presenter
import kotlinx.coroutines.experimental.channels.SubscriptionReceiveChannel
import kotlinx.coroutines.experimental.channels.consumeEach

class TodoListPresenter(
    private val store: TodoListStore
): Presenter<TodoListPresenter.View>() {

    private var subscription: SubscriptionReceiveChannel<TodoListState>? = null

    override fun onViewAttached() {
        subscribeToStore()
    }

    private fun subscribeToStore() = bg {
        subscription = store.subscribe().subscribe()
        subscription!!.consumeEach {
            renderTodo(it)
        }
    }

    private fun renderTodo(state: TodoListState) = perform { view?.render(state) }

    override fun onViewDetached() {
        subscription?.close()
    }

    fun onAddTodoTap() = view?.showAddToDoDialog()

    fun addTodo(text: String) {
        if (!text.isEmpty()) bg { store.dispatch(AddTodoAction(text)) }
    }

    interface View: Presenter.View {
        fun render(state: TodoListState)
        fun showAddToDoDialog()
    }
}