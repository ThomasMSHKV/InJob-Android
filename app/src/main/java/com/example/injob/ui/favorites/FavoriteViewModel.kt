package com.example.injob.ui.favorites

import androidx.lifecycle.ViewModel
import com.example.injob.data.db.AdDao
import com.example.injob.data.db.AdEntity
import kotlinx.coroutines.*

class FavoriteViewModel(private val adDao: AdDao) : ViewModel() {

    fun getAllAds(): List<AdEntity>? {
        val ads = CoroutineScope(Dispatchers.IO).async {
            adDao.getAllAds()
        }

        return runBlocking { ads.await() }
    }

    fun getAllLikedAds(): List<AdEntity>? {
        val likedAds = CoroutineScope(Dispatchers.IO).async {
            adDao.getAllLikedAds(true)
        }

        return runBlocking { likedAds.await() }
    }

    fun getAllRespondedAds(): List<AdEntity>? {
        val respondedAds = CoroutineScope(Dispatchers.IO).async {
            adDao.getAllRespondedAds(true)
        }

        return runBlocking { respondedAds.await() }
    }

    fun updateAd(adEntity: AdEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            adDao.updateAd(adEntity)
        }
    }
}