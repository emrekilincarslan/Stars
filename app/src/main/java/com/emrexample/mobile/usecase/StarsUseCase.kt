package com.emrexample.mobile.usecase

import com.emrexample.mobile.repositories.StarsRepository
import com.emrexample.mobile.repositories.StarsRepository.StarDataState
import io.reactivex.Observable
import javax.inject.Inject

class StarsUseCase @Inject constructor(
    private val starsRepository: StarsRepository
) {
    fun getStarsDataState(): Observable<StarDataState> {
        return starsRepository.getStars()
            .map<StarDataState> {
                if (!it.result.equals("ok") ) StarDataState.Error(it.result)
                else StarDataState.Success(it.response)
            }
            .onErrorReturn { error -> StarDataState.Error(error.message) }
    }

}