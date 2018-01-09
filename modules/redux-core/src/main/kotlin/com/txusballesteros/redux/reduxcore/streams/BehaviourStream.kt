package com.txusballesteros.redux.reduxcore.streams

import com.txusballesteros.redux.reduxcore.State
import kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.experimental.channels.SubscriptionReceiveChannel

abstract class BehaviourStream<T: State>: Stream<T> {
    private val stream = ConflatedBroadcastChannel<T>()

    override val value: T?
        get() = stream.valueOrNull

    override fun offer(state: T) {
        stream.valueOrNull?.let {
            if (it != state) stream.offer(state)
        } ?: stream.offer(state)
    }

    override fun subscribe(): SubscriptionReceiveChannel<T> = stream.openSubscription()
}