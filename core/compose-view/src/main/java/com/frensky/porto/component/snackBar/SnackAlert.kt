package com.frensky.porto.component.snackBar

import com.frensky.porto.component.alert.AlertType

data class SnackAlert (
    val type: AlertType,
    val title: String? = null,
    val message: String? = null,
)