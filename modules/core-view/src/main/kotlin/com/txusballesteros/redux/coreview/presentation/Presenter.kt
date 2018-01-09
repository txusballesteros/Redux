/*
 * Copyright Txus Ballesteros 2018 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
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