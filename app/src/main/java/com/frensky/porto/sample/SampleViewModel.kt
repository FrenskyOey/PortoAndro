package com.frensky.porto.sample

import androidx.lifecycle.viewModelScope
import com.frensky.porto.base.extension.cooperativeLaunch
import com.frensky.porto.base.mvi.MviViewModel
import com.frensky.porto.domain.model.SampleRequest
import com.frensky.porto.domain.repo.SampleRepository
import javax.inject.Inject

class SampleViewModel @Inject
constructor(
    val sampleRepo : SampleRepository
) : MviViewModel<SampleIntent, SampleState, SampleEvent>(SampleState()) {

    override fun dispatch(intent: SampleIntent) {
        when(intent){
            is SampleIntent.reloadData ->{
                reloadData()
                pushEffect(SampleEvent.toLandingPage)
            }
        }
    }

    private fun reloadData(){
        viewModelScope.cooperativeLaunch(
            block = {
                val result = sampleRepo.getSampleData(SampleRequest("",""))
                updateState { copy(
                    param1 = result.data1,
                    param2 = result.data2
                ) }
            },
            onError = {error ->
                updateState {
                    copy(
                        errorString = error.message?:"Error"
                    )
                }
            }
        )
    }

}