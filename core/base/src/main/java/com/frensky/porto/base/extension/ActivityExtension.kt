package com.frensky.porto.base.extension

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.frensky.porto.base.R

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    withAnimated: Boolean = false,
) {
    supportFragmentManager
        .beginTransaction()
        .apply {
            if (withAnimated) {
                setCustomAnimations(
                    R.anim.slide_in_from_right_to_left,
                    R.anim.slide_out_from_right_to_left,
                    R.anim.slide_in_from_left_to_right,
                    R.anim.slide_out_from_left_to_right,
                )
            }
            replace(frameId, fragment)
            addToBackStack(fragment::class.java.canonicalName)
        }.commit()
}

fun AppCompatActivity.replaceFragmentWithoutBackstack(
    fragment: Fragment,
    frameId: Int,
    withAnimated: Boolean = false,
) {
    supportFragmentManager
        .beginTransaction()
        .apply {
            if (withAnimated) {
                setCustomAnimations(
                    R.anim.slide_in_from_right_to_left,
                    R.anim.slide_out_from_right_to_left,
                    R.anim.slide_in_from_left_to_right,
                    R.anim.slide_out_from_left_to_right,
                )
            }
            replace(frameId, fragment)
        }.commit()
}

fun AppCompatActivity.addFragment(
    fragment: Fragment,
    frameId: Int,
    animated: Boolean = true,
) {
    supportFragmentManager
        .beginTransaction()
        .apply {
            if (animated) {
                setCustomAnimations(
                    R.anim.slide_in_from_right_to_left,
                    R.anim.slide_out_from_right_to_left,
                    R.anim.slide_in_from_left_to_right,
                    R.anim.slide_out_from_left_to_right,
                )
            }
            add(frameId, fragment)
            addToBackStack(fragment::class.java.canonicalName)
        }.commit()
}

fun AppCompatActivity.getActivityName(): String = this.javaClass.simpleName

fun AppCompatActivity.getCurrentPackageName(): String = this.javaClass.canonicalName

fun Activity.hideSoftKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}