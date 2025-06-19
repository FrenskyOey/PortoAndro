package com.frensky.porto.data.source.sample.remote

import com.frensky.porto.data.api.SampleApiService
import com.frensky.porto.data.di.NetworkModule
import com.frensky.porto.data.model.BaseResponse
import com.frensky.porto.data.model.SampleRequestData
import com.frensky.porto.data.model.SampleResponseData
import com.frensky.porto.data.source.sample.SampleDataSource
import com.frensky.porto.domain.model.SampleRequest
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class RemoteSampleDataSource
@Inject
constructor(
    @Named(NetworkModule.API_SERVICE) private val apiService: SampleApiService,
) : SampleDataSource.Remote {
    override suspend fun getSample(requestId: String): Response<BaseResponse<SampleResponseData>> {
        return apiService.getSampleData(requestId)
    }

    override suspend fun postSample(request: SampleRequest): Response<BaseResponse<SampleResponseData>> {
        val req = SampleRequestData(request.data1)
        return apiService.postSampleData(req)
    }
}
