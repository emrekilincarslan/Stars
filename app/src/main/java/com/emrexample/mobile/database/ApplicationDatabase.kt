package com.emrexample.mobile.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emrexample.mobile.data.StarDao
import com.emrexample.mobile.model.Star

@Database(
    entities = [Star::class],
    version = 1,exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun StarDao(): StarDao


}