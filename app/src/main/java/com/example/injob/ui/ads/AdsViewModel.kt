package com.example.injob.ui.ads

import androidx.lifecycle.ViewModel
import com.example.injob.data.db.AdDao
import com.example.injob.data.db.AdEntity

class AdsViewModel(private val adDao: AdDao): ViewModel() {

    fun getAllAds(adEntity: AdEntity) {
        adDao.getAllAds()
    }

    fun insertAd(adEntity: AdEntity) {
        adDao.insertAd(adEntity)
    }

    fun updateAd(adEntity: AdEntity) {
        adDao.updateAd(adEntity)
    }

    fun deleteAd(adEntity: AdEntity) {
        adDao.deleteAd(adEntity)
    }
}