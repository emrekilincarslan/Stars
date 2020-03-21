package com.emrexample.mobile.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emrexample.mobile.model.Star
import com.emrexample.mobile.repositories.StarsRepository.StarDataState
import com.emrexample.mobile.usecase.StarsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SelectionOfStarViewModel @Inject constructor(
    private val starsUseCase: StarsUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()
    val viewState = MutableLiveData<SelectionOfStarsViewState>()
    val actionState = MutableLiveData<SelectionOfStarsActionState>()

    init {
        getStars()
    }

    fun getStars() {
        disposables.add(
            starsUseCase.getStarsDataState()
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    return@map when (it) {
                        is StarDataState.Success -> {
                            SelectionOfStarsViewState.ShowStars(it.starList)
                        }
                        is StarDataState.Error -> {
                            val errorMessage = it.errorMessage
                            actionState.value = SelectionOfStarsActionState.ShowSecondaryError(errorMessage)
                            if (viewState.value is SelectionOfStarsViewState.ShowStars) {
                                viewState.value
                            } else {
                                SelectionOfStarsViewState.ShowError(errorMessage)
                            }

                        }
                    }
                }
                .doOnSubscribe { viewState.value = SelectionOfStarsViewState.Loading }
                .subscribe { viewState ->
                    this.viewState.value = viewState
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    sealed class SelectionOfStarsViewState {
        object Loading : SelectionOfStarsViewState()
        data class ShowStars(val starModel: List<Star>) : SelectionOfStarsViewState()
        data class ShowError(val errorMessage: String?) : SelectionOfStarsViewState()
    }

    sealed class SelectionOfStarsActionState {
        data class ShowSecondaryError(val errorMessage: String?) : SelectionOfStarsActionState()
    }
}