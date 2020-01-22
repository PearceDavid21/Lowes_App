package com.xfinity.data

import com.xfinity.data.model.response.Article
import com.xfinity.data.remote.NewsService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(private val newsService: NewsService) {

    fun getNewsHeadlines(country:String): Single<List<Article>> {
        return newsService.getNews(country, apiKey).map {
            it.articles
        }
    }

    companion object {
        val apiKey = "f40ce765a62645df980634f4a8ea4eac"
    }

}