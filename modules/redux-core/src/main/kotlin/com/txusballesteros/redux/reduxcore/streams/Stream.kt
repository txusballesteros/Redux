package com.txusballesteros.redux.reduxcore.streams

import com.txusballesteros.redux.reduxcore.State
import kotlinx.coroutines.experimental.channels.SubscriptionReceiveChannel

interface Stream<T: State> {
    val value: T?
    fun offer(state: T)
    fun subscribe(): SubscriptionReceiveChannel<T>
}