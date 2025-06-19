package com.frensky.porto.data.source.sample

import com.frensky.porto.data.local.database.entity.SampleEntity
import com.frensky.porto.data.model.BaseResponse
import com.frensky.porto.data.model.SampleResponseData
import com.frensky.porto.domain.model.SampleRequest
import retrofit2.Response

interface SampleDataSource {
    interface Remote {
        suspend fun postSample(request: SampleRequest): Response<BaseResponse<SampleResponseData>>
        suspend fun getSample(requestId: String): Response<BaseResponse<SampleResponseData>>
    }
    interface Local {
        suspend fun saveSampleId(accountId:String)
        suspend fun getSampleId(): String

        suspend fun saveSampleData(sampleId:String, param1:String, param2:String)
        suspend fun getSampleData(): List<SampleEntity>
    }
}