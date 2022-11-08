package com.example.injob.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AdEntity::class], version = 1)
abstract class RoomSearchDb : RoomDatabase() {

    abstract fun adDao(): AdDao?

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