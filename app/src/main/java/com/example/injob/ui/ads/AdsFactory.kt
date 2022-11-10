package com.example.injob.ui.ads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.injob.data.db.AdDao

class AdsFactory(private val adDao: AdDao?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return adDao?.let { AdsViewModel(it) } as T
    }
}