package com.txusballesteros.redux.todolist.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.txusballesteros.redux.core.di.coreComponent
import com.txusballesteros.redux.todolist.presentation.TodoListPresenter

internal val featureComponent = Kodein {
    extend(coreComponent)

    bind<TodoListPresenter>() with provider { TodoListPresenter(instance()) }
}
