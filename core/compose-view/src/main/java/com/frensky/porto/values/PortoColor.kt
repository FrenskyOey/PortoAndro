package com.frensky.porto.values

import androidx.compose.ui.graphics.Color
import com.frensky.porto.values.color.main.ColorAccent
import com.frensky.porto.values.color.main.ColorNeutral
import com.frensky.porto.values.color.main.ColorPrimary
import com.frensky.porto.values.color.misc.ColorCharcoal
import com.frensky.porto.values.color.misc.ColorIndigo
import com.frensky.porto.values.color.misc.ColorKhaki
import com.frensky.porto.values.color.misc.ColorLime
import com.frensky.porto.values.color.misc.ColorRose
import com.frensky.porto.values.color.misc.ColorSky
import com.frensky.porto.values.color.misc.ColorViolet
import com.frensky.porto.values.color.misc.ColorYellow
import com.frensky.porto.values.color.semantic.ColorBlue
import com.frensky.porto.values.color.semantic.ColorGreen
import com.frensky.porto.values.color.semantic.ColorOrange
import com.frensky.porto.values.color.semantic.ColorRed

interface PortoColor  {

    // basic colors
    val white: Color
    val black: Color
    val transparent: Color

    // main colors
    val accent: ColorAccent
    val neutral: ColorNeutral
    val primary: ColorPrimary

    // semantic colors
    val blue: ColorBlue
    val green: ColorGreen
    val orange: ColorOrange
    val red: ColorRed

    // misc colors
    val charcoal: ColorCharcoal
    val indigo: ColorIndigo
    val khaki: ColorKhaki
    val lime: ColorLime
    val rose: ColorRose
    val sky: ColorSky
    val violet: ColorViolet
    val yellow: ColorYellow
}

object PortoColorImpl : PortoColor {
    override val white: Color = Color(color = 0xFFFFFFFF)
    override val black: Color = Color(color = 0xFF000000)
    override val transparent: Color = Color(color = 0x00000000)

    override val accent: ColorAccent = ColorAccent()
    override val neutral: ColorNeutral = ColorNeutral()
    override val primary: ColorPrimary = ColorPrimary()

    override val blue: ColorBlue = ColorBlue()
    override val green: ColorGreen = ColorGreen()
    override val orange: ColorOrange = ColorOrange()
    override val red: ColorRed = ColorRed()

    override val charcoal: ColorCharcoal = ColorCharcoal()
    override val indigo: ColorIndigo = ColorIndigo()
    override val khaki: ColorKhaki = ColorKhaki()
    override val lime: ColorLime = ColorLime()
    override val rose: ColorRose = ColorRose()
    override val sky: ColorSky = ColorSky()
    override val violet: ColorViolet = ColorViolet()
    override val yellow: ColorYellow = ColorYellow()
}