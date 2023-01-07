package com.example.abcd.api

import com.example.abcd.modelclasses.NewsResponse
import com.example.abcd.utility.Constants.Companion.API_KEY
import com.example.abcd.utility.Constants.Companion.END_POINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?domains=wsj.com&apiKey=ccb6c64a2a11496889b3a688cd4b7ebf

interface NewsApi {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getNews(@Query("country") country:String, @Query("page") page:Int) : Call<NewsResponse>
//    @GET("v2/top-headlines?apiKey=$API_KEY")
//    fun getHeadlines(@Query("country") country:String, @Query("page") page:Int) : Call<NewsResponse>
}