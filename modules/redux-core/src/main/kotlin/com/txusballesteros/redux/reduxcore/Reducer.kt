package com.txusballesteros.redux.reduxcore

interface Reducer<T: State> {
    fun reduce(action: Action, oldState: T): T
}