package com.txusballesteros.redux.coreview.view

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class AbsFragment: Fragment() {
  private val layoutResourceId by lazy {
    onRequestLayoutResourceId()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    = inflater.inflate(layoutResourceId, container, false)

  @LayoutRes
  abstract fun onRequestLayoutResourceId(): Int

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    onViewReady(savedInstanceState)
  }

  abstract fun onViewReady(savedInstanceState: Bundle?)
}