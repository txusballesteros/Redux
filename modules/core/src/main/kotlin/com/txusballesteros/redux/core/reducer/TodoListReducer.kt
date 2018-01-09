package com.txusballesteros.redux.core.reducer

import com.txusballesteros.redux.reduxcore.Action
import com.txusballesteros.redux.reduxcore.Reducer
import com.txusballesteros.redux.core.action.AddTodoAction
import com.txusballesteros.redux.core.state.TodoListState
import com.txusballesteros.redux.core.state.TodoState

class TodoListReducer: Reducer<TodoListState> {
    override fun reduce(action: Action, oldState: TodoListState): TodoListState =
        when (action) {
            is AddTodoAction -> {
                with(oldState.list.toMutableList()) {
                    add(TodoState(action.text))
                    TodoListState(this)
                }
            }
            else -> oldState
        }
}