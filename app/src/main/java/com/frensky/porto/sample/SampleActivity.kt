package com.frensky.porto.sample

import androidx.hilt.navigation.compose.hiltViewModel
import com.frensky.porto.base.activity.CoreBaseActivity
import com.frensky.porto.base.mvi.MviComposeView

class SampleActivity :
    CoreBaseActivity(),
    MviComposeView<SampleIntent, SampleState, SampleEvent, SampleViewModel> {


    override val viewModel: SampleViewModel = hiltViewModel()

    override fun handleEffect(effect: SampleEvent) {
        TODO("Not yet implemented")
    }

    override fun renderState(state: SampleState) {
        super.renderState(state)
    }
}