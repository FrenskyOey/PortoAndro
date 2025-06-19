package com.frensky.porto.util

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.frensky.porto.theme.PColor

fun Modifier.rippleClickable(onClick: () -> Unit): Modifier =
    composed {
        this.clickable(
            interactionSource =
                remember {
                    MutableInteractionSource()
                },
            indication = ripple(),
            onClick = onClick,
        )
    }

fun Modifier.conditional(
    condition: Boolean,
    modifiers: Modifier.() -> Modifier,
): Modifier =
    if (condition) {
        this.modifiers()
    } else {
        this
    }

fun Modifier.crop(
    horizontal: Dp = 0.dp,
    vertical: Dp = 0.dp,
): Modifier =
    this.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        fun Dp.toPxInt(): Int = this.toPx().toInt()
        layout(
            placeable.width - (horizontal * 2).toPxInt(),
            placeable.height - (vertical * 2).toPxInt(),
        ) {
            placeable.placeRelative(-horizontal.toPx().toInt(), -vertical.toPx().toInt())
        }
    }

fun Modifier.shimmer(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -size.width.toFloat(),
        targetValue = size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = LinearEasing
            )
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                PColor.neutral.neutral_50.copy(alpha = 0.5f),
                PColor.transparent,
                PColor.neutral.neutral_50.copy(alpha = 0.5f),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}