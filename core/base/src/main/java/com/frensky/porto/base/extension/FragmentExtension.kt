package com.frensky.porto.base.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.frensky.porto.base.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private var flContainer : Int? = null
fun Fragment.getFragmentName(): String {
    return this.javaClass.simpleName
}

fun Fragment.getCurrentPackageName(): String {
    return this.javaClass.canonicalName
}

fun FragmentManager.replaceFragment(fragment: Fragment, animated: Boolean = true, flContainerId: Int? = null) {
    flContainerId?.let { flContainer = it }
    flContainer?.let {
        beginTransaction().apply {
            if (animated) {
                setCustomAnimations(
                    R.anim.slide_in_from_right_to_left,
                    R.anim.slide_out_from_right_to_left,
                    R.anim.slide_in_from_left_to_right,
                    R.anim.slide_out_from_left_to_right
                )
            }
            replace(it, fragment)
            addToBackStack(fragment::class.java.canonicalName)
        }.commit()
    }
}

fun FragmentManager.replaceFragmentWithoutBackstack(fragment: Fragment, animated: Boolean = true, flContainerId: Int? = null) {
    flContainerId?.let { flContainer = it }
    flContainer?.let {
        beginTransaction().apply {
            if (animated) {
                setCustomAnimations(
                    R.anim.slide_in_from_right_to_left,
                    R.anim.slide_out_from_right_to_left,
                    R.anim.slide_in_from_left_to_right,
                    R.anim.slide_out_from_left_to_right
                )
            }
            replace(it, fragment)
        }.commit()
    }
}

fun FragmentManager.addFragment(fragment: Fragment, animated: Boolean = true, flContainerId: Int? = null) {
    flContainerId?.let { flContainer = it }
    flContainer?.let {
        beginTransaction().apply {
            if (animated) {
                setCustomAnimations(
                    R.anim.slide_in_from_right_to_left,
                    R.anim.slide_out_from_right_to_left,
                    R.anim.slide_in_from_left_to_right,
                    R.anim.slide_out_from_left_to_right
                )
            }
            add(it, fragment)
            addToBackStack(fragment::class.java.canonicalName)
        }.commit()
    }
}

/**
 * Launches a new coroutine and repeats `block` every time the Fragment's viewLifecycleOwner
 * is in and out of `minActiveState` lifecycle state.
 */
inline fun Fragment.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}

fun Fragment.hideSoftKeyboard() {
    val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
}

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

fun <T : Any> Fragment.autoCleanBinding(initializer: (() -> T)? = null): AutoCleanedValue<T> {
    return AutoCleanedValue(this, initializer)
}