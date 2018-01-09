package com.txusballesteros.redux.coreview.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup

fun ViewGroup.inflate(@LayoutRes layoutResourceId: Int)
    = LayoutInflater.from(this.context).inflate(layoutResourceId, this, false)