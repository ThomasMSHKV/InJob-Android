package com.example.injob.ui.search.viewmodel

import androidx.lifecycle.ViewModel
import com.example.injob.data.db.AdDao
import com.example.injob.data.db.AdEntity
import kotlinx.coroutines.*

class SearchViewModel(private val adDao: AdDao) : ViewModel() {

    fun getAd(id: Long): AdEntity {
        val ads = CoroutineScope(Dispatchers.IO).async {
            adDao.getAd(id)
        }

        return runBlocking { ads.await() }
    }

    fun getAllAds(): List<AdEntity>? {
        val ads = CoroutineScope(Dispatchers.IO).async {
            adDao.getAllAds()
        }

        return runBlocking { ads.await() }
    }

    fun insertAd(adEntity: AdEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            adDao.insertAd(adEntity)
        }
    }

    fun updateAd(adEntity: AdEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            adDao.updateAd(adEntity)
        }
    }

    fun deleteAd(adEntity: AdEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            adDao.deleteAd(adEntity)
        }
    }
}