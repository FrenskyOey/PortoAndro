package com.frensky.porto.base.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.margin(left: Float? = null, top: Float? = null, right: Float? = null, bottom: Float? = null) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
    }
}

fun View.margin(all: Float? = null) {
    this.margin(
        left = all,
        right = all,
        top = all,
        bottom = all,
    )
}

fun View.margin(horizontal: Float? = null, vertical: Float? = null) {
    this.margin(
        left = horizontal,
        right = horizontal,
        top = vertical,
        bottom = vertical,
    )
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun visibleMultiple(vararg views: View?) {
    views.forEach {
        it?.visible()
    }
}

fun goneMultiple(vararg views: View?) {
    views.forEach {
        it?.gone()
    }
}

fun View.visibility(visible: Boolean){
    if(visible)
        this.visible()
    else
        this.gone()
}

fun TextView.textHideIfEmpty(text: String?) {
    if(text.isNullOrEmpty()) {
        gone()
    } else {
        this.text = text
        visible()
    }
}

fun TextView.setHtmlText(content: String) {
    text = HtmlCompat.fromHtml(content, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

fun TextView.updateDrawables(
    left: Drawable? = compoundDrawables[0],
    top: Drawable? = compoundDrawables[1],
    right: Drawable? = compoundDrawables[2],
    bottom: Drawable? = compoundDrawables[3]
) {
    setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)
fun Context.dpToPx(dp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()