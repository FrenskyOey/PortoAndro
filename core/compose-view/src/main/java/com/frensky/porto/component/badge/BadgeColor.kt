package com.frensky.porto.component.badge

import androidx.compose.ui.graphics.Color
import com.frensky.porto.values.PortoColorImpl

enum class BadgeColor(
    val containerColor: Color,
    val borderColor: Color,
    val tintColor: Color
) {
    Yellow(
        containerColor = PortoColorImpl.orange.orange_10,
        borderColor = PortoColorImpl.orange.orange_20,
        tintColor = PortoColorImpl.orange.orange_30
    ),
    Blue(
        containerColor = PortoColorImpl.blue.blue_10,
        borderColor = PortoColorImpl.blue.blue_20,
        tintColor = PortoColorImpl.blue.blue_30
    ),
    Red(
        containerColor = PortoColorImpl.red.red_10,
        borderColor = PortoColorImpl.red.red_20,
        tintColor = PortoColorImpl.red.red_30
    ),
    Green(
        containerColor = PortoColorImpl.green.green_10,
        borderColor = PortoColorImpl.green.green_20,
        tintColor = PortoColorImpl.green.green_30
    ),
    Grey(
        containerColor = PortoColorImpl.neutral.neutral_30,
        borderColor = PortoColorImpl.neutral.neutral_50,
        tintColor = PortoColorImpl.neutral.neutral_90
    ),
    Purple(
        containerColor = PortoColorImpl.violet.violet_10,
        borderColor = PortoColorImpl.violet.violet_20,
        tintColor = PortoColorImpl.violet.violet_30
    );
}