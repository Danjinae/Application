package com.danjinae

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient : Application() {

    var networkService: RetrofitService

    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://101.101.219.69:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    init {
        networkService = retrofit.create(RetrofitService::class.java)
    }
}