package com.xfinity.data.remote

import com.xfinity.data.model.response.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {
    @GET("top-headlines")
    fun getNews(@Query("country") country:String, @Query("apiKey") apikey:String): Single<NewsResponse>
}