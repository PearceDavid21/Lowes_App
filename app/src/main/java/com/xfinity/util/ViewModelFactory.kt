package com.xfinity.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.xfinity.data.DataManager

import com.xfinity.features.masterdetail.NewsListViewModel

import javax.inject.Inject

class ViewModelFactory @Inject
constructor(private val dataManager: DataManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == NewsListViewModel::class.java) return NewsListViewModel(dataManager) as T
        throw RuntimeException("Cannot create an instance of $modelClass", ClassNotFoundException("Class not supported in ViewModelFactory"))
    }
}