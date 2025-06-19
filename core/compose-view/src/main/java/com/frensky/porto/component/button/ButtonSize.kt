package com.frensky.porto.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.frensky.porto.values.PortoTypographyImpl

sealed class ButtonSize(
    val fontStyle: TextStyle,
    val buttonHeight: Dp,
    val contentPadding: PaddingValues
) {
    data object Normal : ButtonSize(
        fontStyle = PortoTypographyImpl.bodyLargeBold,
        buttonHeight = 48.dp,
        contentPadding = PaddingValues(
            horizontal = 16.dp, vertical = 12.dp
        )
    )

    data object Small : ButtonSize(
        fontStyle = PortoTypographyImpl.bodySmallBold,
        buttonHeight = 40.dp,
        contentPadding = PaddingValues(
            horizontal = 16.dp, vertical = 8.dp
        )
    )

    data class Custom(
        val customFontStyle: TextStyle,
        val customHeight: Dp,
        val customContentPadding: PaddingValues
    ) : ButtonSize(
        fontStyle = customFontStyle,
        buttonHeight = customHeight,
        contentPadding = customContentPadding
    )
}