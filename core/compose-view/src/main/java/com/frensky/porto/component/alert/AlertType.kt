package com.frensky.porto.component.alert

import androidx.compose.ui.graphics.Color
import com.frensky.porto.compose.R
import com.frensky.porto.values.PortoColorImpl

enum class AlertType(
    val icon: Int,
    val titleColor: Color,
    val messageColor: Color,
    val containerColor: Color,
    val borderColor: Color
) {
    INFORMATION(
        icon = R.drawable.ic_information_circle,
        titleColor = PortoColorImpl.blue.blue_50,
        messageColor = PortoColorImpl.blue.blue_40,
        containerColor = PortoColorImpl.blue.blue_10,
        borderColor = PortoColorImpl.blue.blue_20
    ),
    WARNING(
        icon = R.drawable.ic_warning,
        titleColor = PortoColorImpl.orange.orange_50,
        messageColor = PortoColorImpl.orange.orange_40,
        containerColor = PortoColorImpl.orange.orange_10,
        borderColor = PortoColorImpl.orange.orange_20
    ),
    POSITIVE(
        icon = R.drawable.ic_check_circle,
        titleColor = PortoColorImpl.green.green_50,
        messageColor = PortoColorImpl.green.green_40,
        containerColor = PortoColorImpl.green.green_10,
        borderColor = PortoColorImpl.green.green_20
    ),
    NEGATIVE(
        icon = R.drawable.ic_x_circle,
        titleColor = PortoColorImpl.red.red_50,
        messageColor = PortoColorImpl.red.red_40,
        containerColor = PortoColorImpl.red.red_10,
        borderColor = PortoColorImpl.red.red_20
    ),
    NEUTRAL(
        icon = R.drawable.ic_exclamation_circle,
        titleColor = PortoColorImpl.neutral.neutral_100,
        messageColor = PortoColorImpl.neutral.neutral_90,
        containerColor = PortoColorImpl.neutral.neutral_30,
        borderColor = PortoColorImpl.neutral.neutral_50
    )
}