package com.frensky.porto.data.model

import com.google.gson.annotations.SerializedName

data class SampleRequestData(
    @SerializedName("sample_param")
    var param1: String? = null,
)