package com.frensky.porto.domain.repo

import com.frensky.porto.domain.model.SampleModel
import com.frensky.porto.domain.model.SampleRequest

interface SampleRepository {
    suspend fun getSampleData(request: SampleRequest): SampleModel
    suspend fun postSampleData(request: SampleRequest): SampleModel
    suspend fun getSampleFromDB(): List<SampleModel>
}


