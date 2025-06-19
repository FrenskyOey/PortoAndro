package com.frensky.porto.component.radioButton

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.frensky.porto.theme.PColor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RadioButtonView(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = PColor.primary.base
) {
    var checkState by remember { mutableStateOf(false) }

    val dotRadius = animateDpAsState(
        targetValue = if (selected) 12.dp / 2 else 0.dp,
        animationSpec = tween(durationMillis = 100)
    )
    val backgroundColor = if (enabled) PColor.white else PColor.neutral.neutral_30
    val contentColor = if (enabled) color else PColor.neutral.neutral_50

    Canvas(
        modifier = modifier
            .selectable(
                selected = selected,
                onClick = {
                    checkState = !selected
                    onClick()
                },
                enabled = enabled,
                role = Role.RadioButton,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = false,
                    radius = 20.dp
                )
            )
            .wrapContentSize(Alignment.Center)
            .padding(2.dp)
            .requiredSize(24.dp)
    ) {
        val strokeWidth = 2.dp.toPx()
        drawCircle(
            color = backgroundColor,
            radius = 12.dp.toPx() - strokeWidth / 2,
            style = Fill
        )
        drawCircle(
            color = contentColor,
            radius = 12.dp.toPx() - strokeWidth / 2,
            style = Stroke(strokeWidth)
        )
        if (dotRadius.value > 0.dp) {
            drawCircle(
                color = contentColor,
                radius = dotRadius.value.toPx() - strokeWidth / 2,
                style = Fill
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun RadioButtonViewPreview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var isSelected by remember { mutableStateOf(true) }
        RadioButtonView(
            enabled = true,
            selected = isSelected,
            onClick = {
                isSelected = !isSelected
            }
        )
        RadioButtonView(
            enabled = false,
            selected = false,
            onClick = {
                isSelected = !isSelected
            }
        )
        RadioButtonView(
            enabled = false,
            selected = true,
            onClick = {
                isSelected = !isSelected
            }
        )
    }
}