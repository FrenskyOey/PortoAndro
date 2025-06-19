package com.frensky.porto.values

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface PortoSpacing {
    val xxxs: Dp
    val xxs: Dp
    val xs: Dp
    val sm: Dp
    val md: Dp
    val lg: Dp
    val xl: Dp
    val xxl: Dp
    val xxxl: Dp
    val xxxxl: Dp
    val xxxxxl: Dp
    val xxxxxxl: Dp
    val xxxxxxxl: Dp
    val xxxxxxxxl: Dp
    val xxxxxxxxxl: Dp
    val xxxxxxxxxxl: Dp
}

object PortoSpacingImpl : PortoSpacing {
    override val xxxs = 4.dp
    override val xxs = 8.dp
    override val xs = 12.dp
    override val sm = 16.dp
    override val md = 24.dp
    override val lg = 32.dp
    override val xl = 40.dp
    override val xxl = 48.dp
    override val xxxl = 56.dp
    override val xxxxl = 64.dp
    override val xxxxxl = 72.dp
    override val xxxxxxl = 80.dp
    override val xxxxxxxl = 88.dp
    override val xxxxxxxxl = 96.dp
    override val xxxxxxxxxl = 104.dp
    override val xxxxxxxxxxl = 112.dp
}