package com.frensky.porto.data.model

import com.frensky.porto.domain.model.SampleModel
import com.google.gson.annotations.SerializedName

data class SampleResponseData(
    @SerializedName("title") val param1: String? = "",
    @SerializedName("content") val param2: String? = ""
) : ModelEntity<SampleModel> {
    override fun mapToEntity(): SampleModel {
        return SampleModel(
            data1 = param1 ?: "",
            data2 = param2 ?: "",
        )
    }
}