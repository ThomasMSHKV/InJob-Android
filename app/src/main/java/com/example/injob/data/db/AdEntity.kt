package com.example.injob.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URI

@Entity(tableName = "searchinfo")
data class AdEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Long = 0 ,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "imageIsChosen") val imageIsChosen: Boolean,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "payment") val payment: String,
    @ColumnInfo(name = "location") val location: String
)