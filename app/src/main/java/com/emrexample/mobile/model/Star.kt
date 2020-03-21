package com.emrexample.mobile.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stars")
data class Star(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val star_id:String,
    val starName:String)