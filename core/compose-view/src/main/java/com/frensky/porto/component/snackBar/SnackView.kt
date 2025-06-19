package com.frensky.porto.component.snackBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frensky.porto.component.alert.AlertView
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
fun rememberSnackState(): SnackState = remember { SnackState() }

@Suppress("ktlint:standard:function-naming")
@Composable
fun SnackView(
    modifier: Modifier = Modifier,
    state: SnackState,
    position: SnackPosition = SnackPosition.Float,
    duration: Long = 3000L,
) {
    Box(
        modifier =
            modifier
                .fillMaxSize(),
    ) {
        SnackComponent(
            state = state,
            duration = duration,
            position = position,
        )
    }
}

@Composable
internal fun SnackComponent(
    state: SnackState,
    duration: Long = 3000L,
    position: SnackPosition = SnackPosition.Float,
) {
    var showSnack by remember { mutableStateOf(false) }
    val alert by rememberUpdatedState(newValue = state.alert.value)

    val enterAnimation: EnterTransition =
        expandVertically(
            animationSpec = tween(delayMillis = 300),
            expandFrom =
                when (position) {
                    is SnackPosition.Bottom -> Alignment.Bottom
                    is SnackPosition.Float -> Alignment.CenterVertically
                },
        )

    val exitAnimation: ExitTransition =
        shrinkVertically(
            animationSpec = tween(delayMillis = 300),
            shrinkTowards =
                when (position) {
                    is SnackPosition.Bottom -> Alignment.Bottom
                    is SnackPosition.Float -> Alignment.CenterVertically
                },
        )

    DisposableEffect(
        key1 = state.updateState,
    ) {
        showSnack = true
        val timer = Timer("Animation Timer", true)
        timer.schedule(duration) {
            showSnack = false
        }
        onDispose {
            timer.cancel()
            timer.purge()
        }
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(
                    bottom =
                        when (position) {
                            is SnackPosition.Bottom -> 0.dp
                            is SnackPosition.Float -> 24.dp
                        },
                    start = 16.dp,
                    end = 16.dp,
                ),
        verticalArrangement =
            when (position) {
                is SnackPosition.Bottom -> Arrangement.Bottom
                is SnackPosition.Float -> Arrangement.Bottom
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedVisibility(
            visible = state.isNotEmpty() && showSnack,
            enter =
                when (position) {
                    is SnackPosition.Bottom -> enterAnimation
                    is SnackPosition.Float -> fadeIn()
                },
            exit =
                when (position) {
                    is SnackPosition.Bottom -> exitAnimation
                    is SnackPosition.Float -> fadeOut()
                },
        ) {
            alert?.let {
                AlertView(
                    type = it.type,
                    message = it.message,
                    title = it.title,
                    onCloseIconClicked = {
                        showSnack = false
                    },
                )
            }
        }
    }
}