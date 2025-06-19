package com.frensky.porto.base.extension

import android.util.Log
import com.frensky.porto.model.exception.APIError.APIAuthException
import com.frensky.porto.model.exception.APIException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

fun CoroutineScope.cooperativeLaunch(
    block: suspend CoroutineScope.() -> Unit,
    onError: suspend CoroutineScope.(e: Exception) -> Unit = {},
    onTerminate: () -> Unit = {},
): Job =
    launch {
        try {
            block()
        } catch (e: APIAuthException) {
            // do nothing since handled by event bus
            Log.e("Expired-401", e.message)
        } catch (e: Exception) {
            if (e is APIException) {
                val message = "API Exception : ${e.errorCode} | ${e.urlRequest} | ${e.originMessage}"
                Log.e("Server Error", message)
            }
            onError(e)
        } finally {
            onTerminate()
        }
    }

fun CoroutineScope.cooperativeAsync(
    block: suspend CoroutineScope.() -> Unit,
    onError: suspend CoroutineScope.(e: Exception) -> Unit = {},
    onTerminate: () -> Unit = {},
): Deferred<Unit> =
    async {
        try {
            block()
        } catch (e: APIAuthException) {
            // do nothing since handled by event bus
            Log.e("Expired-401", e.message)
        } catch (e: Exception) {
            if (e is APIException) {
                val message = "API Exception : ${e.errorCode} | ${e.urlRequest} | ${e.originMessage}"
                Log.e("Server Error", message)
            }
            onError(e)
        } finally {
            onTerminate()
        }
    }