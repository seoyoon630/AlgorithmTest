package com.bri.androidstudy.core.servicelocator

import com.bri.androidstudy.core.platform.BaseInterceptor
import com.bri.androidstudy.data.MainDataSource
import com.bri.androidstudy.data.MainRepositoryImpl
import com.bri.androidstudy.domain.MainRepository
import com.bri.androidstudy.domain.usecase.GetList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceLocator {
    private val retrofit: Retrofit = createRetrofit()

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(BaseInterceptor())
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()
    }

    private fun provideMainDataSource(): MainDataSource {
        return retrofit.create(MainDataSource::class.java)
    }

    private fun provideMainRepository(): MainRepository {
        return MainRepositoryImpl(provideMainDataSource())
    }

    fun provideGetList(): GetList {
        return GetList(provideMainRepository())
    }
}