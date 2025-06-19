package com.frensky.porto.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonColors

data class ButtonSpec(
    val colors: ButtonColors,
    val border: BorderStroke? = null
)