package com.frensky.porto.component.button.type

import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import com.frensky.porto.component.button.ButtonSpec
import com.frensky.porto.theme.PColor

class ButtonPrimaryInverted {

    @Composable
    private fun basePrimaryInvertedColors() = ButtonColors(
        containerColor = PColor.white,
        contentColor = PColor.primary.base,
        disabledContainerColor = PColor.neutral.neutral_30,
        disabledContentColor = PColor.neutral.neutral_60
    )

    @Composable
    fun default() = ButtonSpec(
        colors = basePrimaryInvertedColors()
    )

    @Composable
    fun hover() = ButtonSpec(
        colors = basePrimaryInvertedColors().copy(
            contentColor = PColor.primary.hover
        )
    )

    @Composable
    fun selected() = ButtonSpec(
        colors = basePrimaryInvertedColors().copy(
            contentColor = PColor.primary.selected
        )
    )
}