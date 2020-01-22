package com.xfinity.features.masterdetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.xfinity.data.model.response.Article
import com.xfinity.data.model.response.RelatedTopic
import org.greenrobot.eventbus.EventBus

class CharacterViewModel : ViewModel() {
    val articleImage = MutableLiveData<String>()
    val author = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val source = MutableLiveData<String>()
    val url = MutableLiveData<String>()

    fun bind(article : Article) {
        articleImage.value = article.urlToImage
        author.value = article.author
        title.value = article.title
        article.source?.let {
            source.value = it.name
        }
        url.value = article.url
    }

    fun openArticleDetail() {
        EventBus.getDefault().post(url.value)
    }
}