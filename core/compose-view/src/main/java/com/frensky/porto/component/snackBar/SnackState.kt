package com.frensky.porto.component.snackBar

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SnackState {
    private val _message = mutableStateOf<SnackAlert?>(null)
    val alert: State<SnackAlert?> = _message

    var updateState by  mutableStateOf(false)
        private set

    fun addAlert(alerts: SnackAlert) {
        _message.value = alerts
        updateState = !updateState
    }

    fun isNotEmpty(): Boolean {
        return _message.value != null
    }
}