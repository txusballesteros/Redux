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