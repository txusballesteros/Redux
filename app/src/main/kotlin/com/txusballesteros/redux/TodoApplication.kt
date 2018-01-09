package com.txusballesteros.redux

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.txusballesteros.redux.threading.APPLICATION_BG
import com.txusballesteros.redux.threading.APPLICATION_MAIN
import kotlinx.coroutines.experimental.android.HandlerContext
import kotlinx.coroutines.experimental.newFixedThreadPoolContext

class TodoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        APPLICATION_MAIN = HandlerContext(Handler(Looper.getMainLooper()), "UI")
        APPLICATION_BG = newFixedThreadPoolContext(2 * Runtime.getRuntime().availableProcessors(), "bg")
    }
}