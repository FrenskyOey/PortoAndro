package com.frensky.porto.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data") val data: T,
    @SerializedName("message") val error: String?,
    @SerializedName("status") val success: Boolean?,
    @SerializedName("response_code") val responseCode: Int? = null,
)