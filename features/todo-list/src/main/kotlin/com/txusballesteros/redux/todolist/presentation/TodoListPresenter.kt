package com.txusballesteros.redux.todolist.presentation

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

    interface View: Presenter.View {
        fun render(state: TodoListState)
    }
}