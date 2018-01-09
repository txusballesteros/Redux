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