package com.ibrahim.nbafacts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ibrahim.nbafacts.model.Player
import com.ibrahim.nbafacts.model.PlayerMetaData
import com.ibrahim.nbafacts.service.NBAApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PlayersViewModel(application: Application) : BaseViewModel(application) {

    private val nbaApiService = NBAApiService()

    // 'disposable' method makes it disposable the retrofit calls
    private val disposable = CompositeDisposable()

    val playersList = MutableLiveData<List<Player>>()
    val playersLoading = MutableLiveData<Boolean>()
    val playersError = MutableLiveData<Boolean>()
    var page = MutableLiveData<Int>()
    var totalPage = MutableLiveData<Int>()
    var searchQuery = MutableLiveData<String>()

    fun getData() {
        playersLoading.value = true
        disposable.add(
            nbaApiService.getPlayers(page.value.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PlayerMetaData>() {
                    override fun onSuccess(t: PlayerMetaData) {
                        playersList.value = t.player
                        page.value = t.meta.currentPage.toInt()
                        totalPage.value = t.meta.totalPages.toInt()
                        playersError.value = false
                        playersLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        playersLoading.value = false
                        playersError.value = true
                    }
                })
        )
    }


    fun searchPlayer() {
        playersLoading.value = true
        disposable.add(
            nbaApiService.searchPlayer(searchQuery.value.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PlayerMetaData>() {
                    override fun onSuccess(t: PlayerMetaData) {
                        playersList.value = t.player
                        Log.i("totalcount", t.meta.totalCount)
                        Log.i("searchplayer", searchQuery.value.toString())
                        page.value = t.meta.currentPage.toInt()
                        totalPage.value = t.meta.totalPages.toInt()
                        playersError.value = false
                        playersLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        playersLoading.value = false
                        playersError.value = true
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("onclear", "onclear")
        disposable.clear()
    }

}