package com.example.injob.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AdsEntity::class], version = 1)
abstract class RoomSearchDb : RoomDatabase {

    abstract fun adsDao(): AdsDao?

    companion object {
        private var INSTANCE: RoomSearchDb? = null

        fun getAppDatabase(context: Context): RoomSearchDb? {

            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder<RoomSearchDb>(
                    context.applicationContext, RoomSearchDb::class.java, "searchDB"
                ).allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}