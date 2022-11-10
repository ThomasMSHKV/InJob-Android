package com.example.injob.ui.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.injob.data.db.AdDao

class SearchFactory(private val adDao: AdDao?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return adDao?.let { SearchViewModel(it) } as T
    }
}