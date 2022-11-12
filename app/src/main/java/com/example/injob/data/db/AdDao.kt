package com.example.injob.data.db

import androidx.room.*

@Dao
interface AdDao {

    @Query("SELECT * FROM searchinfo WHERE id = :id")
    fun getAd(id: Long): AdEntity

    @Query("SELECT * FROM searchinfo ORDER BY id DESC")
    fun getAllAds(): List<AdEntity>?

    @Query("SELECT * FROM searchinfo WHERE isLiked = :isLiked")
    fun getAllLikedAds(isLiked: Boolean = true): List<AdEntity>?

    @Query("SELECT * FROM searchinfo WHERE isResponded = :isResponded")
    fun getAllRespondedAds(isResponded: Boolean = true): List<AdEntity>?

    @Insert
    fun insertAd(ads: AdEntity?)

    @Delete
    fun deleteAd(ads: AdEntity?)

    @Update
    fun updateAd(ads: AdEntity?)
}