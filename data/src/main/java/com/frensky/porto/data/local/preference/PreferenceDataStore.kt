package com.frensky.porto.data.local.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class PreferenceDataStore(private val delegate: DataStore<Preferences>) :
    DataStore<Preferences> by delegate {

    fun <T> getFlow(key: Preferences.Key<T>): Flow<T?> {
        return data.map { it[key] }
    }

    suspend fun <T> getValue(key: Preferences.Key<T>): T? {
        return data.firstOrNull()?.get(key)
    }

    suspend fun <T> edit(key: Preferences.Key<T>, value: T) {
        edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun <T> remove(key: Preferences.Key<T>) {
        edit { preferences ->
            preferences.remove(key)
        }
    }

    suspend fun clear() {
        edit { preferences -> preferences.clear() }
    }
}