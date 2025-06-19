package com.frensky.porto.component.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.frensky.porto.component.button.ButtonView
import com.frensky.porto.theme.PColor
import com.frensky.porto.theme.PTypography

@Composable
fun EmptyScreen(
    message: String,
    buttonLabel: String,
    modifier: Modifier = Modifier,
    illustrationPainter: Painter? = null,
    title: String = "",
    onButtonClick: () -> Unit
) {
    Column(
        modifier = modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        illustrationPainter?.let { painter ->
            Image(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(128.dp),
                painter = painter,
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = title,
            style = PTypography.bodyLargeBold.copy(
                color = PColor.primary.base
            ),
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = message,
            style = PTypography.bodySmall.copy(
                color = PColor.neutral.neutral_80
            ),
            textAlign = TextAlign.Center
        )
        ButtonView (
            modifier = Modifier.fillMaxWidth(),
            text = buttonLabel,
            onClick = {
                onButtonClick()
            }
        )
    }
}