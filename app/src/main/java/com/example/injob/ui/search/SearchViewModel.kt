package com.example.injob.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.injob.data.db.AdEntity
import com.example.injob.data.db.RoomSearchDb

class SearchViewModel(app: Application) : AndroidViewModel(app) {

    var allAds: MutableLiveData<List<AdEntity>?> = MutableLiveData()

    fun getAllAds() {
        val adsDao = RoomSearchDb.getAppDatabase((getApplication()))?.adDao()
        val list = adsDao?.getAllAds()

        allAds.value = list
    }

    fun insertAd(entity: AdEntity) {
        val userDao = RoomSearchDb.getAppDatabase(getApplication())?.adDao()
        userDao?.insertAd(entity)
        getAllAds()
    }

    fun updateAd(entity: AdEntity) {
        val userDao = RoomSearchDb.getAppDatabase(getApplication())?.adDao()
        userDao?.updateAd(entity)
        getAllAds()
    }

    fun deleteAd(entity: AdEntity) {
        val userDao = RoomSearchDb.getAppDatabase(getApplication())?.adDao()
        userDao?.deleteAd(entity)
        getAllAds()
    }
}