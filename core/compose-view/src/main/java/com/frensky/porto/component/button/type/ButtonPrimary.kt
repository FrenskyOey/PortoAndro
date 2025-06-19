package com.frensky.porto.component.button.type

import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import com.frensky.porto.component.button.ButtonSpec
import com.frensky.porto.theme.PColor

class ButtonPrimary {

    @Composable
    private fun basePrimaryColors() = ButtonColors(
        containerColor = PColor.primary.base,
        contentColor = PColor.white,
        disabledContainerColor = PColor.neutral.neutral_30,
        disabledContentColor = PColor.neutral.neutral_60
    )

    @Composable
    fun default() = ButtonSpec(
        colors = basePrimaryColors()
    )

    @Composable
    fun hover() = ButtonSpec(
        colors = basePrimaryColors().copy(
            containerColor = PColor.primary.hover
        )
    )

    @Composable
    fun selected() = ButtonSpec(
        colors = basePrimaryColors().copy(
            containerColor = PColor.primary.selected
        )
    )
}