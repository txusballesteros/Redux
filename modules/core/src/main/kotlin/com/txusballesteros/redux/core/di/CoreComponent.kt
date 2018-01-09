package com.txusballesteros.redux.core.di

import com.github.salomonbrys.kodein.Kodein

val coreComponent = Kodein {
    import(coreModule)
}
