package com.frensky.porto.sample

import com.frensky.porto.component.screen.ScreenState

sealed interface SampleIntent {
    data object reloadData : SampleIntent
}

sealed interface SampleEvent {
    data object toLandingPage : SampleEvent
}

data class SampleState(
    val param1: String? = null,
    val param2: String? = null,
    val errorString: String? = null,
    val displayState: ScreenState = ScreenState.Idle,
)