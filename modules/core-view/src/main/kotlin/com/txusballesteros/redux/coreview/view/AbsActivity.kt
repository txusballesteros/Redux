package com.txusballesteros.redux.coreview.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.txusballesteros.redux.coreview.extensions.attach

abstract class AbsActivity: AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState == null) {
      supportFragmentManager.attach(android.R.id.content, onRequestFragment())
    }
  }

  abstract fun onRequestFragment() : Fragment
}