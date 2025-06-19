package com.frensky.porto.component.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.frensky.porto.compose.R
import com.frensky.porto.theme.PColor

@Composable
fun ScreenContent(
    screenState: ScreenState,
    modifier: Modifier = Modifier,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    onReload: () -> Unit = {},
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    loading: @Composable () -> Unit = {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(56.dp),
                strokeWidth = 8.dp,
                color = PColor.primary.base,
                trackColor = PColor.primary.surface
            )
        }
    },
    error: @Composable () -> Unit = {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            EmptyScreen(
                modifier = Modifier.padding(horizontal = 32.dp),
                title = stringResource(R.string.error_state_title),
                message = stringResource(R.string.error_state_message),
                buttonLabel = stringResource(R.string.error_state_retry_button_label),
                onButtonClick = onReload
            )
        }
    },
    loadingDialog: @Composable () -> Unit = {
        LoadingScreen()
    },
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = contentWindowInsets,
        floatingActionButtonPosition = floatingActionButtonPosition,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .then(modifier)
        ) {
            when (screenState) {
                // show nothing, can be used as initial state if needed
                ScreenState.Idle -> {}
                ScreenState.Loading -> loading()
                ScreenState.Error -> error()
                ScreenState.Success, ScreenState.PopupLoading -> content()
            }

            if (screenState == ScreenState.PopupLoading) {
                loadingDialog()
            }
        }
    }
}