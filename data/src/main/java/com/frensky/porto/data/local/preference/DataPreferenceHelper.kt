package com.frensky.porto.data.local.preference

import android.content.SharedPreferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.frensky.porto.data.local.preference.PreferenceExtension.put
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataPreferenceHelper
@Inject
constructor(
    val preference: SharedPreferences,
    val dataStore: PreferenceDataStore,
) : DataPreference {
    companion object {
        const val PREF_KEY_AUTH_TOKEN = "pref_auth_token"
    }

    override fun setAuthToken(token: String) {
        preference.put(PREF_KEY_AUTH_TOKEN, token)
    }

    override fun getAuthToken(): String? {
        return preference.getString(PREF_KEY_AUTH_TOKEN, null)
    }

    override fun getBoolean(key: String): Boolean = preference.getBoolean(key, false)

    override suspend fun getBooleanFlow(key: String): Flow<Boolean> {
        val prefKey = booleanPreferencesKey(key)
        return dataStore.getFlow(prefKey).map { it ?: false }
    }

    override suspend fun getBooleanValue(key: String): Boolean {
        val prefKey = booleanPreferencesKey(key)
        return dataStore.getValue(prefKey) ?: false
    }

    override fun getLong(key: String): Long = preference.getLong(key, 0L)

    override suspend fun getLongFlow(key: String): Flow<Long> {
        val prefKey = longPreferencesKey(key)
        return dataStore.getFlow(prefKey).map { it ?: 0L }
    }

    override suspend fun getLongValue(key: String): Long {
        val prefKey = longPreferencesKey(key)
        return dataStore.getValue(prefKey) ?: 0L
    }

    override fun getString(key: String): String = preference.getString(key, "") ?: ""

    override suspend fun getStringFlow(key: String): Flow<String> {
        val prefKey = stringPreferencesKey(key)
        return dataStore.getFlow(prefKey).map { it ?: "" }
    }

    override suspend fun getStringValue(key: String): String {
        val prefKey = stringPreferencesKey(key)
        return dataStore.getValue(prefKey) ?: ""
    }

    override fun edit(
        key: String,
        value: Any,
    ) {
        preference.put(key, value)
    }

    override suspend fun editValue(
        key: String,
        value: Any,
    ) {
        when (value) {
            is String -> {
                val prefKey = stringPreferencesKey(key)
                dataStore.edit(prefKey, value)
            }
            is Long -> {
                val prefKey = longPreferencesKey(key)
                dataStore.edit(prefKey, value)
            }
            is Boolean -> {
                val prefKey = booleanPreferencesKey(key)
                dataStore.edit(prefKey, value)
            }
            is Int -> {
                val prefKey = longPreferencesKey(key)
                dataStore.edit(prefKey, value.toLong())
            }
        }
    }

    override suspend fun deleteAllData() {
        preference.edit().clear().commit()
        dataStore.clear()
    }
}