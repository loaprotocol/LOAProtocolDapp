package com.goodchoice.android.loalending.network

import com.goodchoice.android.loalending.network.service.NetworkService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 1L
private const val READ_TIMEOUT = 20L


fun networkModule(baseUrl: String) = module {

    single(createdAtStart = true) {
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            addInterceptor(AddCookiesInterceptor())
            addInterceptor(ReceivedCookiesInterceptor())
//            addInterceptor(ChangeableBaseUrlInterceptor())

        }.build()
    }

    single(createdAtStart = true) {
        val gson= GsonBuilder().setLenient().create()
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(NetworkService::class.java)
    }


}