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
package com.txusballesteros.redux.core.reducer

import com.txusballesteros.redux.core.action.AddTodoAction
import com.txusballesteros.redux.core.state.TodoListState
import com.txusballesteros.redux.reduxcore.Action
import com.txusballesteros.redux.reduxcore.Reducer

class TodoListReducer: Reducer<TodoListState> {
    override fun reduce(action: Action, oldState: TodoListState): TodoListState =
        when (action) {
            is AddTodoAction -> addNewToDo(oldState, action)
            else -> oldState
        }

    private fun addNewToDo(currentState: TodoListState, action: AddTodoAction): TodoListState
        = with(currentState.list.toMutableList()) {
            add(com.txusballesteros.redux.core.state.TodoState(action.text))
            com.txusballesteros.redux.core.state.TodoListState(this)
        }
}