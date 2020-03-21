package com.emrexample.mobile.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emrexample.mobile.model.Star
import io.reactivex.Single

@Dao
interface StarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStars(stars: List<Star>)

    @Query("select * from stars")
    fun getStars(): Single<List<Star>>
}