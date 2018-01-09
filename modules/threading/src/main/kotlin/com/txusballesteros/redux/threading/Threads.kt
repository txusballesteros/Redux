package com.txusballesteros.redux.threading

import kotlin.coroutines.experimental.CoroutineContext
import kotlin.properties.Delegates

var APPLICATION_MAIN : CoroutineContext by Delegates.notNull()
var APPLICATION_BG : CoroutineContext by Delegates.notNull()
