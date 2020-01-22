package com.xfinity.data

import com.xfinity.data.model.response.Article
import com.xfinity.data.model.response.Character
import com.xfinity.data.model.response.NewsResponse
import com.xfinity.data.model.response.RelatedTopic
import com.xfinity.data.remote.CharactersService

import javax.inject.Inject
import javax.inject.Singleton

import io.reactivex.Single

@Singleton
class DataManager @Inject
constructor(private val charactersService: CharactersService) {

    fun getCharacters(q: String): Single<List<RelatedTopic>?> {
        return charactersService.getCharacters(q, FORMAT).map(Character::relatedTopics)
    }

    fun getNewsHeadlines(country:String): Single<List<Article>> {
        return charactersService.getNews(country, apiKey).map {
            it.articles
        }
    }

    companion object {
        private val FORMAT = "json"
        val apiKey = "f40ce765a62645df980634f4a8ea4eac"
        val apiURL = "https://newsapi.org/v2/"
    }

}