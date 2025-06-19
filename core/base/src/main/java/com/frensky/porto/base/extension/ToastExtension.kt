package com.frensky.porto.base.extension

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.frensky.porto.base.R

fun Context.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    val toast = Toast(this)
    val view: View = View.inflate(this, R.layout.toast_layout, null)
    val tvToast = view.findViewById<TextView>(R.id.tvToast)
    tvToast.text = msg
    toast.view = view
    toast.duration = duration
    toast.show()
}

fun Fragment.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    val context = requireContext()
    context.showToast(msg,duration)
}