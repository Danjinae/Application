package com.danjinae

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient : Application() {

    var networkService: RetrofitService

    private val client: OkHttpClient
    init {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY }
        client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }



//
//
//    var okHttpClient: OkHttpClient = OkHttpClient.Builder()
//        .connectTimeout(30, TimeUnit.MINUTES)
//        .readTimeout(30, TimeUnit.SECONDS)
//        .writeTimeout(30, TimeUnit.SECONDS)
//        .build()


    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://101.101.219.69:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    init {
        networkService = retrofit.create(RetrofitService::class.java)
    }
}