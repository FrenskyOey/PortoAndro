package com.frensky.porto.data.source.sample.local

import com.frensky.porto.data.local.database.dao.SampleDao
import com.frensky.porto.data.local.database.entity.SampleEntity
import com.frensky.porto.data.local.preference.DataPreference
import com.frensky.porto.data.source.sample.SampleDataSource
import javax.inject.Inject

class LocalSampleDataSource
@Inject
constructor(
    private val pref: DataPreference,
    private val sampleDao: SampleDao,
) : SampleDataSource.Local {

    override suspend fun saveSampleId(accountId: String) {
        pref.editValue("SAMPLE_ID",accountId)
    }

    override suspend fun getSampleId(): String {
      return pref.getStringValue("SAMPLE_ID")
    }

    override suspend fun saveSampleData(sampleId: String, param1: String, param2: String) {
        val entity = SampleEntity()
        entity.id = sampleId
        entity.param1 = param1
        entity.param2 = param2

        sampleDao.saveData(entity)
    }

    override suspend fun getSampleData():  List<SampleEntity> {
        return sampleDao.getSampleData()
    }

}