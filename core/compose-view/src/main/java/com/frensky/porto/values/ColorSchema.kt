package com.frensky.porto.values

import androidx.compose.material3.lightColorScheme

val portoColorScheme = lightColorScheme(
    primary = PortoColorImpl.primary.base,
    onPrimary = PortoColorImpl.white,
    inversePrimary = PortoColorImpl.white,
    surface = PortoColorImpl.white,
    error = PortoColorImpl.red.red_30,
    onError = PortoColorImpl.white,
    errorContainer = PortoColorImpl.red.red_10,
    onErrorContainer = PortoColorImpl.red.red_30,
)