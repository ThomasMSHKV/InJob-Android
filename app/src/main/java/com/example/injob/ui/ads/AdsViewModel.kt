package com.example.injob.ui.ads

import androidx.lifecycle.ViewModel
import com.example.injob.data.db.AdDao
import com.example.injob.data.db.AdEntity
import kotlinx.coroutines.*

class AdsViewModel(private val adDao: AdDao) : ViewModel() {

    fun getAllAds(adEntity: AdEntity): List<AdEntity>? {
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