package com.txusballesteros.redux.coreview.extensions

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import org.funktionale.option.Option
import kotlin.reflect.KClass

inline fun <reified T: Fragment> FragmentManager.ifIsNotAttached(block: (FragmentManager) -> Unit) {
  if (findByTag<T>().isEmpty()) block(this)
}

inline fun <reified T: Fragment> FragmentManager.findByTag() : Option<T> {
  val tag = getTag(T::class)
  return (this.findFragmentByTag(tag) as? T)?.let { Option.Some(it) } ?: Option.None
}

fun FragmentManager.add(@IdRes placeHolder: Int, fragment: Fragment) {
  if (!fragment.isAdded) {
    val tag = getTag(fragment::class)
    beginTransaction()
        .add(placeHolder, fragment, tag)
        .commit()
  }
}

fun FragmentManager.attach(@IdRes placeHolder: Int, fragment: Fragment) {
  val tag = getTag(fragment::class)
  beginTransaction()
      .replace(placeHolder, fragment, tag)
      .commit()
}

fun getTag(type: KClass<*>) = type.java.name