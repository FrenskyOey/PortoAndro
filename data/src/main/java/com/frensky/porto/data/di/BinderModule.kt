package com.frensky.porto.data.di

import com.frensky.porto.data.repository.SampleDataRepository
import com.frensky.porto.data.source.sample.SampleDataSource
import com.frensky.porto.data.source.sample.local.LocalSampleDataSource
import com.frensky.porto.data.source.sample.remote.RemoteSampleDataSource
import com.frensky.porto.domain.repo.SampleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {
    @Binds
    abstract fun provideSampleLocalData(impl: LocalSampleDataSource): SampleDataSource.Local

    @Binds
    abstract fun provideSampleRemoteData(impl: RemoteSampleDataSource): SampleDataSource.Remote

    @Binds
    abstract fun provideSampleRepository(impl: SampleDataRepository): SampleRepository
}