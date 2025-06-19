package com.frensky.porto.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.frensky.porto.data.local.database.AppDatabase
import com.frensky.porto.data.local.database.Migration
import com.frensky.porto.data.local.database.dao.SampleDao
import com.frensky.porto.data.local.preference.DataPreference
import com.frensky.porto.data.local.preference.DataPreferenceHelper
import com.frensky.porto.data.local.preference.PreferenceDataStore
import com.frensky.porto.data.local.preference.PreferenceKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    // local data provider
    @Provides
    @Singleton
    fun provideContext(
        @ApplicationContext context: Context,
    ): Context = context

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        try {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, "porto_db")
                .allowMainThreadQueries()
                .addMigrations(
                    Migration.MIGRATION_1_2,
                ).fallbackToDestructiveMigration()
                .build()
        } catch (e: Exception) {
            throw e
        }
    }

    @Singleton
    @Provides
    fun provideSampleDao(database: AppDatabase): SampleDao = database.sampleDao()

    @Singleton
    @Provides
    fun provideSharedPreference(
        @ApplicationContext context: Context,
    ): SharedPreferences {
        val pref =
            context.getSharedPreferences(PreferenceKey.PREF_GENERAL_NAME, Context.MODE_PRIVATE)
        return pref
    }

    @Singleton
    @Provides
    fun providePreferenceDataStore(
        @ApplicationContext context: Context,
    ): PreferenceDataStore =
        PreferenceDataStore(
            PreferenceDataStoreFactory.create(
                produceFile = { context.preferencesDataStoreFile(PreferenceKey.PREF_DATA_STORE_NAME) },
            ),
        )

    @Singleton
    @Provides
    fun provideDataPreference(
        preference: SharedPreferences,
        dataStore: PreferenceDataStore,
    ): DataPreference = DataPreferenceHelper(preference, dataStore)
}