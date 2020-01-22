package com.xfinity.data

import com.xfinity.data.model.response.Article
import com.xfinity.data.remote.CharactersService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(private val charactersService: CharactersService) {

    fun getNewsHeadlines(country:String): Single<List<Article>> {
        return charactersService.getNews(country, apiKey).map {
            it.articles
        }
    }

    companion object {
        val apiKey = "f40ce765a62645df980634f4a8ea4eac"
    }

}