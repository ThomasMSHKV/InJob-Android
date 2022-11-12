package com.example.injob.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.injob.data.db.AdDao

class FavoriteFactory (private val adDao: AdDao?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return adDao?.let { FavoriteViewModel(it) } as T
    }
}