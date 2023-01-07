package com.example.abcd.api

import com.example.abcd.utility.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    private val appRetrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
    val myApi by lazy {
        appRetrofit.create(NewsApi::class.java)
    }

}