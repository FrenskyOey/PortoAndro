package com.frensky.porto.values

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.frensky.porto.compose.R

interface PortoTypography {
    val displayLarge: TextStyle
    val displayMedium: TextStyle
    val displaySmall: TextStyle
    val headingLarge: TextStyle
    val headingMedium: TextStyle
    val headingSmall: TextStyle
    val bodyLarge: TextStyle
    val bodySmall: TextStyle
    val captionLarge: TextStyle
    val captionSmall: TextStyle

    val displayLargeBold: TextStyle
    val displayMediumBold: TextStyle
    val displaySmallBold: TextStyle
    val headingLargeBold: TextStyle
    val headingMediumBold: TextStyle
    val headingSmallBold: TextStyle
    val bodyLargeBold: TextStyle
    val bodySmallBold: TextStyle
    val bodySmallSemiBold: TextStyle
    val captionLargeBold: TextStyle
    val captionSmallBold: TextStyle
}

object PortoTypographyImpl : PortoTypography {

    private val fontProvider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs,
    )
    private val nunitoGoogleFont = GoogleFont("Nunito")
    private val nunitoFontFamily = FontFamily(
        Font(nunitoGoogleFont, fontProvider, FontWeight.Normal),
        Font(nunitoGoogleFont, fontProvider, FontWeight.Normal, FontStyle.Italic),
        Font(nunitoGoogleFont, fontProvider, FontWeight.SemiBold),
        Font(nunitoGoogleFont, fontProvider, FontWeight.SemiBold, FontStyle.Italic),
        Font(nunitoGoogleFont, fontProvider, FontWeight.Bold),
        Font(nunitoGoogleFont, fontProvider, FontWeight.Bold, FontStyle.Italic)
    )
    private val basicFontFamily = TextStyle(
        fontFamily = nunitoFontFamily,
        color = PortoColorImpl.neutral.neutral_100,
        fontWeight = FontWeight.Normal,
    )

    override val displayLarge: TextStyle = basicFontFamily.copy(
        fontSize = 64.sp, lineHeight = 80.sp
    )

    override val displayMedium: TextStyle = basicFontFamily.copy(
        fontSize = 56.sp, lineHeight = 64.sp
    )

    override val displaySmall: TextStyle = basicFontFamily.copy(
        fontSize = 48.sp, lineHeight = 56.sp
    )

    override val headingLarge: TextStyle = basicFontFamily.copy(
        fontSize = 40.sp, lineHeight = 48.sp
    )

    override val headingMedium: TextStyle = basicFontFamily.copy(
        fontSize = 32.sp, lineHeight = 40.sp
    )

    override val headingSmall: TextStyle = basicFontFamily.copy(
        fontSize = 24.sp, lineHeight = 32.sp
    )

    override val bodyLarge: TextStyle = basicFontFamily.copy(
        fontSize = 16.sp, lineHeight = 24.sp
    )

    override val bodySmall: TextStyle = basicFontFamily.copy(
        fontSize = 14.sp, lineHeight = 22.sp
    )

    override val captionLarge: TextStyle = basicFontFamily.copy(
        fontSize = 12.sp, lineHeight = 16.sp
    )

    override val captionSmall: TextStyle = basicFontFamily.copy(
        fontSize = 10.sp, lineHeight = 15.sp
    )

    override val displayLargeBold: TextStyle = displayLarge.copy(
        fontWeight = FontWeight.Bold
    )

    override val displayMediumBold: TextStyle = displayMedium.copy(
        fontWeight = FontWeight.Bold
    )

    override val displaySmallBold: TextStyle = displaySmall.copy(
        fontWeight = FontWeight.Bold
    )

    override val headingLargeBold: TextStyle = headingLarge.copy(
        fontWeight = FontWeight.Bold
    )

    override val headingMediumBold: TextStyle = headingMedium.copy(
        fontWeight = FontWeight.Bold
    )

    override val headingSmallBold: TextStyle = headingSmall.copy(
        fontWeight = FontWeight.Bold
    )

    override val bodyLargeBold: TextStyle = bodyLarge.copy(
        fontWeight = FontWeight.Bold
    )

    override val bodySmallBold: TextStyle = bodySmall.copy(
        fontWeight = FontWeight.Bold
    )

    override val bodySmallSemiBold: TextStyle = bodySmall.copy(
        fontWeight = FontWeight.SemiBold
    )

    override val captionLargeBold: TextStyle = captionLarge.copy(
        fontWeight = FontWeight.Bold
    )

    override val captionSmallBold: TextStyle = captionSmall.copy(
        fontWeight = FontWeight.Bold
    )
}