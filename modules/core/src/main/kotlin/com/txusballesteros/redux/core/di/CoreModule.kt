package com.txusballesteros.redux.core.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.txusballesteros.redux.core.reducer.TodoListReducer
import com.txusballesteros.redux.core.store.TodoListStore
import com.txusballesteros.redux.core.stream.TodoListStream

internal val coreModule = Kodein.Module {
    bind<TodoListStream>() with singleton { TodoListStream() }
    bind<TodoListStore>() with singleton { TodoListStore(instance(), instance()) }
    bind<TodoListReducer>() with singleton { TodoListReducer() }
}
