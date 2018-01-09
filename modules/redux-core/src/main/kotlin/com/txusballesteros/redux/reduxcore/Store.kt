package com.txusballesteros.redux.reduxcore

import com.txusballesteros.redux.reduxcore.streams.Stream

interface Store<T: State> {
    val currentState: T?
    fun dispatch(action: Action)
    fun subscribe(): Stream<T>
}