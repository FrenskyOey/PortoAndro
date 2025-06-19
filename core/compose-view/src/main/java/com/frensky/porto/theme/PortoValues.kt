package com.frensky.porto.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import com.frensky.porto.values.PortoColor
import com.frensky.porto.values.PortoColorImpl
import com.frensky.porto.values.PortoGradient
import com.frensky.porto.values.PortoGradientImpl
import com.frensky.porto.values.PortoSpacing
import com.frensky.porto.values.PortoSpacingImpl
import com.frensky.porto.values.PortoTypography
import com.frensky.porto.values.PortoTypographyImpl

val LocalPortoColors = compositionLocalOf { PortoColorImpl }
val LocalPortoGradients = compositionLocalOf { PortoGradientImpl }
val LocalPortoTypography = compositionLocalOf { PortoTypographyImpl }
val LocalPortoSpacing = compositionLocalOf { PortoSpacingImpl }

val PColor: PortoColor
    @Composable
    get() = LocalPortoColors.current

val PGradient: PortoGradient
    @Composable
    get() = LocalPortoGradients.current

val PTypography: PortoTypography
    @Composable
    get() = LocalPortoTypography.current

val PSpacing: PortoSpacing
    @Composable
    get() = LocalPortoSpacing.current

