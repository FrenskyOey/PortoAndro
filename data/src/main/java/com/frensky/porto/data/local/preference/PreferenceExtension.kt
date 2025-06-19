package com.frensky.porto.data.local.preference

import android.content.SharedPreferences

object PreferenceExtension {
    //This does not handle the Set<String>
    fun <T> SharedPreferences.put(key: String, input: T) {
        when (input) {
            is Boolean -> {
                this.edit().putBoolean(key, input).apply()
            }
            is Float -> {
                this.edit().putFloat(key, input).apply()
            }
            is Int -> {
                this.edit().putLong(key, input.toLong()).apply()
            }
            is Long -> {
                this.edit().putLong(key, input).apply()
            }
            is String -> {
                this.edit().putString(key, input).apply()
            }
        }
    }


}