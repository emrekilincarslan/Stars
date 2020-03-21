package com.emrexample.mobile.repositories

import com.emrexample.mobile.api.StartsApi
import com.emrexample.mobile.data.StarDao
import com.emrexample.mobile.model.BaseResponseWithData
import com.emrexample.mobile.model.Star
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StarsRepository @Inject constructor(
    private val starDao: StarDao,
    private val startsApi: StartsApi
) {

    /**
     * Chaining query to DB and network data source
     */
    fun getStars(): Observable<BaseResponseWithData<List<Star>>> {
        return Observable.concatArray(
            getStarsFromDb(),
            getStarsFromApi().toObservable()
        )
    }

    private fun getStarsFromApi(): Single<BaseResponseWithData<List<Star>>> {
        return startsApi.getStars()
            .doOnSuccess { baseResponseWithData ->
                storeStarsInDb(baseResponseWithData.response)
            }
            .subscribeOn(Schedulers.io())
    }

    private fun storeStarsInDb(list: List<Star>) {
        starDao.insertStars(list)
    }

    private fun getStarsFromDb(): Observable<BaseResponseWithData<List<Star>>> {
        return starDao.getStars().toObservable()
            .flatMap { list ->
                return@flatMap if (list.isEmpty()) {
                    Observable.empty()
                } else {
                    Observable.just(BaseResponseWithData("ok", list))
                }
            }
    }

    sealed class StarDataState {
        data class Success(val starList: List<Star>) : StarDataState()
        data class Error(val errorMessage: String?) : StarDataState()
    }
}