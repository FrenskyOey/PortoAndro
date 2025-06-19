package com.frensky.porto.data.api

import com.frensky.porto.data.model.BaseResponse
import com.frensky.porto.data.model.SampleRequestData
import com.frensky.porto.data.model.SampleResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SampleApiService {
    @POST("/api/v1/sample")
    suspend fun postSampleData(
        @Body request: SampleRequestData,
    ): Response<BaseResponse<SampleResponseData>>

    @GET("/api/v1/get/sample")
    suspend fun getSampleData(
        @Query("account_xid") accountXid: String
    ): Response<BaseResponse<SampleResponseData>>
}