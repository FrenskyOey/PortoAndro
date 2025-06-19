package com.frensky.porto.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.frensky.porto.values.PortoColorImpl
import com.frensky.porto.values.PortoGradientImpl
import com.frensky.porto.values.PortoSpacingImpl
import com.frensky.porto.values.PortoTypographyImpl
import com.frensky.porto.values.portoColorScheme
import com.frensky.porto.values.portoTypography

@Composable
fun PortoTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalPortoColors provides PortoColorImpl,
        LocalPortoGradients provides PortoGradientImpl,
        LocalPortoTypography provides PortoTypographyImpl,
        LocalPortoSpacing provides PortoSpacingImpl,
    ) {
        MaterialTheme(
            colorScheme = portoColorScheme,
            typography = portoTypography,
            content = content
        )
    }
}