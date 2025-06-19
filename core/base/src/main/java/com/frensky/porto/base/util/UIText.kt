package com.frensky.porto.base.util

import android.content.Context
import androidx.annotation.StringRes

sealed class UIText {
    data class DynamicString(val text: String): UIText()
    data class StringResource(@StringRes val resId: Int, val params: List<Any> = emptyList()): UIText()
    object Empty : UIText()

    fun asString(context: Context): String {
        return when(this) {
            is StringResource -> {
                context.getString(resId, *params.toTypedArray())
            }
            is DynamicString -> text
            is Empty -> ""
        }
    }
}