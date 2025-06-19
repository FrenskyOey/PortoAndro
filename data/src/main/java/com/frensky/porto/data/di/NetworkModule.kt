package com.frensky.porto.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.frensky.porto.data.BuildConfig
import com.frensky.porto.data.api.NetworkMonitor
import com.frensky.porto.data.api.SampleApiService
import com.frensky.porto.data.network.interceptor.BasicInterceptor
import com.frensky.porto.data.util.NetworkStatusHelper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val QUALIFIER_OKHTTP_BUILDER = "OKHTTP_BUILDER"

    private const val PORTO_RETROFIT = "PORTO_RETROFIT"
    private const val MOCK_RETROFIT = "PORTO_MOCK_RETROFIT"

    const val API_SERVICE = "PORTO_API_SERVICE"
    const val MOCK_API_SERVICE = "PORTO_MOCK_API_SERVICE"

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    @Singleton
    @Provides
    fun provideNetworkMonitor(
        @ApplicationContext context: Context,
    ): NetworkMonitor = NetworkStatusHelper(context)

    @Provides
    @Named(QUALIFIER_OKHTTP_BUILDER)
    fun provideOKHttpBuilder(
        @ApplicationContext context: Context,
        gson: Gson,
        networkHelper: NetworkMonitor,
    ): OkHttpClient.Builder {
        val client: OkHttpClient.Builder
        val interceptor = BasicInterceptor(context, gson, networkHelper)

        client =
            OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(ChuckerInterceptor(context))
                .addInterceptor(interceptor)
        return client
    }

    @Singleton
    @Provides
    @Named(PORTO_RETROFIT)
    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder =
        Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.BASE_URL)

    @Singleton
    @Provides
    @Named(API_SERVICE)
    fun provideApiService(
        @Named(QUALIFIER_OKHTTP_BUILDER) clientBuilder: OkHttpClient.Builder,
        @Named(PORTO_RETROFIT) builder: Retrofit.Builder,
    ): SampleApiService =
        builder
            .client(clientBuilder.build())
            .build()
            .create(SampleApiService::class.java)
}