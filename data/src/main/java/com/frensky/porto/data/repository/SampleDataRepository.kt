package com.frensky.porto.data.repository

import com.frensky.porto.data.network.getErrorData
import com.frensky.porto.data.network.isValid
import com.frensky.porto.data.source.sample.SampleDataSource
import com.frensky.porto.domain.model.SampleModel
import com.frensky.porto.domain.model.SampleRequest
import com.frensky.porto.domain.repo.SampleRepository
import javax.inject.Inject

class SampleDataRepository
@Inject
constructor(
    val localData: SampleDataSource.Local,
    val remoteData: SampleDataSource.Remote,
) : SampleRepository {

    override suspend fun getSampleData(request: SampleRequest): SampleModel {
        val response = remoteData.getSample(request.data1)
        if (response.isValid()) {
            val results = response.body()?.data?.mapToEntity()
            localData.saveSampleId(results!!.data1)
            localData.saveSampleData("",results!!.data1,results!!.data2)
            return results!!
        }

        throw response.getErrorData()
    }

    override suspend fun postSampleData(request: SampleRequest): SampleModel {
        val response = remoteData.postSample(request)

        if (response.isValid()) {
            val results = response.body()?.data?.mapToEntity()
            localData.saveSampleId(results!!.data1)
            localData.saveSampleData("",results!!.data1,results!!.data2)
            return results!!
        }
        throw response.getErrorData()
    }

    override suspend fun getSampleFromDB(): List<SampleModel> {
        val data = localData.getSampleData()
        val result = ArrayList<SampleModel>()

        for(entity in data){
            result.add(SampleModel(entity.param1,entity.param2))
        }
        return result
    }

}