package com.ibrahim.nbafacts.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ibrahim.nbafacts.model.Game
import com.ibrahim.nbafacts.model.GameMetaData
import com.ibrahim.nbafacts.service.NBAApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class GamesViewModel(application: Application): BaseViewModel(application) {

    private val nbaApiService = NBAApiService()
    private val disposable = CompositeDisposable()

    val gamesList = MutableLiveData<List<Game>>()
    val gamesLoading = MutableLiveData<Boolean>()
    val gamesError = MutableLiveData<Boolean>()
    var page = MutableLiveData<Int>()
    var totalPage = MutableLiveData<Int>()

    fun getData(){
        gamesLoading.value = true
        disposable.add(
                nbaApiService.getGames(page.value.toString())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<GameMetaData>(){
                            override fun onSuccess(t: GameMetaData) {
                                page.value = t.meta.currentPage.toInt()
                                totalPage.value = t.meta.totalPages.toInt()
                                gamesList.value = t.game
                                gamesError.value = false
                                gamesLoading.value = false
                            }

                            override fun onError(e: Throwable) {
                                gamesLoading.value = false
                                gamesError.value = true
                            }

                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}