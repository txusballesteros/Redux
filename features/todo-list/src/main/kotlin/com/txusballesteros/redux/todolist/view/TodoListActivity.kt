package com.txusballesteros.redux.todolist.view

import android.support.v4.app.Fragment
import com.txusballesteros.redux.coreview.view.AbsActivity

class TodoListActivity: AbsActivity() {
    override fun onRequestFragment(): Fragment = TodoListFragment.newInstance()
}