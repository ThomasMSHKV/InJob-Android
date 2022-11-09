package com.example.injob.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AdDao {

    @Query("SELECT * FROM searchinfo ORDER BY id DESC")
    fun getAllAds(): List<AdEntity>?

    @Insert
    fun insertAd(ads: AdEntity?)

    @Delete
    fun deleteAd(ads: AdEntity?)

    @Update
    fun updateAd(ads: AdEntity?)
}