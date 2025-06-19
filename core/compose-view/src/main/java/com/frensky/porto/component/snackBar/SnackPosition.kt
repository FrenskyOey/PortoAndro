package com.frensky.porto.component.snackBar

sealed class SnackPosition {
    object Bottom: SnackPosition()

    object Float: SnackPosition()
}