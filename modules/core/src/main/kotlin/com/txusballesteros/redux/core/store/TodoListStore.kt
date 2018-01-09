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
package com.txusballesteros.redux.core.store

import com.txusballesteros.redux.reduxcore.Action
import com.txusballesteros.redux.reduxcore.Store
import com.txusballesteros.redux.core.reducer.TodoListReducer
import com.txusballesteros.redux.core.state.TodoListState
import com.txusballesteros.redux.core.state.TodoState
import com.txusballesteros.redux.core.stream.TodoListStream
import com.txusballesteros.redux.reduxcore.streams.Stream

class TodoListStore(
    private val stream: TodoListStream,
    private val reducer: TodoListReducer
) : Store<TodoListState> {

    init {
        stream.offer(currentState)
    }

    override val currentState: TodoListState
        get() = stream.value ?: TodoListState(listOf(TodoState("ToDo Task 1"),
                                                     TodoState("ToDo Task 2"),
                                                     TodoState("ToDo Task 3")))

    override fun dispatch(action: Action) = stream.offer(reducer.reduce(action, currentState))

    override fun subscribe(): Stream<TodoListState> = stream
}