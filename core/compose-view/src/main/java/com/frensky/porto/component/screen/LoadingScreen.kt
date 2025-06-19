package com.frensky.porto.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.frensky.porto.theme.PColor

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    withOverlay: Boolean = true
) {
    if (withOverlay) {
        Dialog(
            onDismissRequest = {},
        ) {
            LoadingView(modifier = modifier)
        }
    } else {
        LoadingView(modifier = modifier)
    }
}

@Composable
private fun LoadingView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    color = PColor.white,
                    shape = RoundedCornerShape(8.dp)
                )
        )
        CircularProgressIndicator(
            modifier = Modifier.size(56.dp),
            strokeWidth = 8.dp,
            color = PColor.primary.base,
            trackColor = PColor.primary.surface
        )
    }
}