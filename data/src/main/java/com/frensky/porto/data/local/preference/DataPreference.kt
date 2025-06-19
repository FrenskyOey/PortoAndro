package com.frensky.porto.data.local.preference

import kotlinx.coroutines.flow.Flow

interface DataPreference {
    fun getAuthToken(): String?
    fun setAuthToken(token: String)

    fun getString(key: String) : String
    fun getLong(key: String) : Long
    fun getBoolean(key: String) : Boolean

    fun edit(key: String, value : Any)

    suspend fun getStringValue(key: String) : String
    suspend fun getLongValue(key: String) : Long
    suspend fun getBooleanValue(key: String) : Boolean
    suspend fun editValue(key: String, value : Any)
    suspend fun getStringFlow(key: String) : Flow<String>
    suspend fun getLongFlow(key: String) : Flow<Long>
    suspend fun getBooleanFlow(key: String) : Flow<Boolean>

    suspend fun deleteAllData()
}