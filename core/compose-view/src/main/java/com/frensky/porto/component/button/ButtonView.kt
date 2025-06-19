package com.frensky.porto.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnit.Companion.Unspecified
import androidx.compose.ui.unit.dp
import com.frensky.porto.component.button.type.ButtonType.primary
import com.frensky.porto.component.button.type.ButtonType.primaryInverted
import com.frensky.porto.theme.PColor

@Composable
fun ButtonView(
    text: String,
    modifier: Modifier = Modifier,
    startIcon: Painter? = null,
    endIcon: Painter? = null,
    enabled: Boolean = true,
    type: ButtonSpec = primary.default(),
    size: ButtonSize = ButtonSize.Normal,
    fontSize: TextUnit = Unspecified,
    onClick: () -> Unit
) {
    val contentColor = if (enabled) type.colors.contentColor else type.colors.disabledContentColor
    val fontStyle = size.fontStyle.copy(
        color = contentColor,
        fontSize = if (fontSize == Unspecified) size.fontStyle.fontSize else fontSize
    )
    Button(
        modifier = modifier.height(size.buttonHeight),
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = type.colors,
        border = type.border?.copy(
            brush = if (enabled) type.border.brush else SolidColor(PColor.neutral.neutral_40)
        ),
        contentPadding = size.contentPadding,
        onClick = {
            onClick.invoke()
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            startIcon?.let {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = it,
                    tint = contentColor,
                    contentDescription = null
                )
            }
            Text(text = text, style = fontStyle)
            endIcon?.let {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = it,
                    tint = contentColor,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun ButtonPrimaryPreview() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // primary default
        ButtonView(
            text = "Button Text",
            onClick = {}
        )
        // primary hover
        ButtonView(
            text = "Button Text",
            type = primary.hover(),
            onClick = {}
        )
        // primary selected
        ButtonView(
            text = "Button Text",
            type = primary.selected(),
            onClick = {}
        )
        // primary disabled
        ButtonView(
            text = "Button Text",
            type = primary.default(),
            enabled = false,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun ButtonInvertedPrimaryPreview() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // primary inverted default
        ButtonView(
            text = "Button Text",
            type = primaryInverted.default(),
            onClick = {}
        )
        // primary inverted hover
        ButtonView(
            text = "Button Text",
            type = primaryInverted.hover(),
            onClick = {}
        )
        // primary inverted selected
        ButtonView(
            text = "Button Text",
            type = primaryInverted.selected(),
            onClick = {}
        )
        // primary inverted disabled
        ButtonView(
            text = "Button Text",
            type = primaryInverted.default(),
            enabled = false,
            onClick = {}
        )
    }
}