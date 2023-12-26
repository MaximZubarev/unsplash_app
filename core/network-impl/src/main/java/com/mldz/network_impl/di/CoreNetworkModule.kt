package com.mldz.network_impl.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mldz.core.network.impl.BuildConfig
import com.mldz.network_impl.retrofit.RetrofitService
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit


private const val API_URL = "https://api.unsplash.com"

@Module
@ComponentScan("com.mldz.network_impl")
class CoreNetworkModule {

    @Single
    fun json() = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Single
    fun retrofit(): RetrofitService {
        val contentType = "application/json".toMediaType()
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(API_URL)
            .addConverterFactory(json().asConverterFactory(contentType))
            .build()
            .create(RetrofitService::class.java)
    }

    private val authInterceptor = Interceptor {
        val originalRequest = it.request()
        val requestWithApiKey = originalRequest.newBuilder()
            .addHeader("Authorization", "Client-ID ${BuildConfig.API_KEY}")
            .build()
        it.proceed(requestWithApiKey)
    }
}
