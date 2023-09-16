package com.scapricorne.core.network.di

import com.scapricorne.core.network.BuildConfig
import com.scapricorne.core.network.JsonConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {
    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
    private const val API_URL = "https://api.punkapi.com/v2/"

    @Provides
    fun provideJsonConverter(): JsonConverter = JsonConverter()

    @Provides
    fun provideRetrofit(jsonConverter: JsonConverter): Retrofit {
        if (BuildConfig.DEBUG)
            initLogging()
        return Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(jsonConverter.factory)
            .client(httpClient.build())
            .build()
    }

    private fun initLogging() {
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
    }
}