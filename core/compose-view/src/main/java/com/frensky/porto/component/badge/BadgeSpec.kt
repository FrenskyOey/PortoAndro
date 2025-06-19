package com.frensky.porto.component.badge

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BadgeSpec(
    val noIconPadding: PaddingValues = PaddingValues(0.dp),
    val hasIconPadding: PaddingValues = PaddingValues(0.dp),
    val shape: Shape = RoundedCornerShape(4.dp),
    val iconSize: Dp = 0.dp
) {
    companion object BadgeSpecs {
        val small: BadgeSpec = BadgeSpec(
            noIconPadding = PaddingValues(horizontal = 16.dp),
            hasIconPadding = PaddingValues(horizontal = 4.dp),
            iconSize = 12.dp
        )
        val large: BadgeSpec = BadgeSpec(
            noIconPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            hasIconPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
            iconSize = 14.dp
        )
    }
}