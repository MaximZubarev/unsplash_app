package com.mldz.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mldz.core.network.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit


val networkModule = module {
    single {
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        val contentType = "application/json".toMediaType()
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
//        Retrofit.Builder()
//            .client(client)
//            .baseUrl(BuildConfig.API_URL)
//            .addConverterFactory(json.asConverterFactory(contentType))
//            .build()
//            .create(com.mldz.network_impl.retrofit.RetrofitService::class.java)
    }
}

private val authInterceptor = Interceptor {
    val originalRequest = it.request()
    val requestWithApiKey = originalRequest.newBuilder()
        .addHeader("Authorization", "Client-ID ${BuildConfig.API_KEY}")
        .build()
    it.proceed(requestWithApiKey)
}