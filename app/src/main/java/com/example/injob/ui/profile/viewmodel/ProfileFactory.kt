package com.example.injob.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.injob.data.db.AdDao
import com.example.injob.ui.search.viewmodel.SearchViewModel

class ProfileFactory(private val adDao: AdDao?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return adDao?.let { ProfileViewModel(it) } as T
    }
}