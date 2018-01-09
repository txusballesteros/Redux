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