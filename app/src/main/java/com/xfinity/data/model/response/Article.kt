package com.xfinity.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class
NewsResponse (val articles: List<Article>?)

data class Article (val author: String?,
                    val title: String?,
                    @SerializedName("urlToImage")
                    @Expose
                    val urlToImage: String?,
                    val url: String?,
                    val source: Source?)

data class Source (val name: String?)