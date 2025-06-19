package com.frensky.porto.base.extension

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Context.color(@ColorRes colorId: Int): Int {
    return ContextCompat.getColor(this, colorId)
}

fun Context.colorStateList(@ColorRes colorId: Int): ColorStateList {
    return AppCompatResources.getColorStateList(this, colorId)
}

fun Context.drawable(@DrawableRes drawableId: Int): Drawable? {
    return AppCompatResources.getDrawable(this, drawableId)
}

fun Fragment.color(@ColorRes colorId: Int): Int {
    return requireContext().color(colorId)
}

fun Fragment.colorStateList(@ColorRes colorId: Int): ColorStateList {
    return requireContext().colorStateList(colorId)
}

fun Fragment.drawable(@DrawableRes drawableId: Int): Drawable? {
    return requireContext().drawable(drawableId)
}

fun View.color(@ColorRes colorId: Int): Int {
    return context.color(colorId)
}

fun View.colorStateList(@ColorRes colorId: Int): ColorStateList {
    return context.colorStateList(colorId)
}

fun View.drawable(@DrawableRes drawableId: Int): Drawable? {
    return context.drawable(drawableId)
}

val View.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)
