package com.frensky.porto.values

import com.frensky.porto.values.gradient.MainGradient
import com.frensky.porto.values.gradient.SecondaryGradient

interface PortoGradient {
    val main: MainGradient
    val secondary: SecondaryGradient
}

object PortoGradientImpl : PortoGradient {
    override val main: MainGradient = MainGradient()
    override val secondary: SecondaryGradient = SecondaryGradient()
}