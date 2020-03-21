package com.emrexample.mobile.api

import com.emrexample.mobile.model.BaseResponseWithData
import com.emrexample.mobile.model.Star
import io.reactivex.Single
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface StartsApi {

    @GET("/stars")
    fun getStars(): Single<BaseResponseWithData<List<Star>>>
}