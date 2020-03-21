package com.emrexample.mobile.dagger

import android.app.Application
import androidx.room.Room
import com.emrexample.mobile.data.StarDao
import com.emrexample.mobile.database.ApplicationDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): ApplicationDatabase {
        return Room.databaseBuilder(
            application,
            ApplicationDatabase::class.java, "example.db"
        )
            .allowMainThreadQueries()
            .build()
    }


    @Singleton
    @Provides
    fun providesStarDao(applicationDatabase: ApplicationDatabase): StarDao {
        return applicationDatabase.StarDao()
    }


    // TODO:  for unit testing
//    @Singleton
//    @Provides
//    fun provideTestRoomDatabase(application: Application): ApplicationDatabase {
//        return Room.inMemoryDatabaseBuilder(
//            application,
//            ApplicationDatabase::class.java
//        )
//            .allowMainThreadQueries()
//            .build()
//    }

}