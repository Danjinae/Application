package com.danjinae.network

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.inject.Inject


object RetrofitClient : Application() {

    var networkService: RetrofitService
    val URL = "http://101.101.219.69:8080/"
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
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    init {
        networkService = retrofit.create(RetrofitService::class.java)
    }

    class NullOnEmptyConverterFactory @Inject constructor() : Converter.Factory() {

        override fun responseBodyConverter(
            type: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ) = Converter<ResponseBody, Any?> {
            if (it.contentLength() != 0L) retrofit.nextResponseBodyConverter<Any?>(
                this,
                type,
                annotations
            ).convert(it) else null
        }
    }
}

