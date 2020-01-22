package com.xfinity.features.masterdetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.xfinity.R
import com.xfinity.data.DataManager
import com.xfinity.data.model.response.Article
import com.xfinity.features.masterdetail.data.adapter.ItemListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel(private val dataManager: DataManager) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val characterListAdapter = ItemListAdapter()
    val errorClickListener = View.OnClickListener { loadCharacters("us") }

    init {
        loadCharacters("us")
    }

    fun loadCharacters(query: String) {
        disposables.add(
                dataManager.getNewsHeadlines(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { onRetrieveCharacterListStart() }
                        .doAfterTerminate { onRetrieveCharacterListFinish() }
                        .subscribe(
                                { result -> onRetrieveCharacterListSuccess(result as List<Article>) },
                                { onRetrievePostListError(it) }
                        )
        )
    }

    override fun onCleared() {
        disposables.clear()
    }

    private fun onRetrieveCharacterListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveCharacterListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCharacterListSuccess(postList:List<Article>) {
        characterListAdapter.updateCharacterList(postList)
    }

    private fun onRetrievePostListError(throwable: Throwable) {
        throwable.printStackTrace()
        errorMessage.value = R.string.post_error
    }
}