package com.txusballesteros.redux.coreview.presentation

import com.txusballesteros.redux.threading.APPLICATION_BG
import com.txusballesteros.redux.threading.APPLICATION_MAIN
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

abstract class Presenter<T: Presenter.View> {
    var view: T? = null
        private set

    fun onViewReady(view: T) {
        this.view = view
        onViewAttached()
    }

    open protected fun onViewAttached() { }

    fun onViewDestroyed() {
        this.view = null
        onViewDetached()
    }

    open protected fun onViewDetached() { }

    protected fun perform(block: () -> Unit) = launch(APPLICATION_MAIN) { block() }

    protected fun bg(block: suspend () -> Unit) = async(APPLICATION_BG) { block() }

    interface View
}