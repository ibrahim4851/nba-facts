package com.ibrahim.nbafacts.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ibrahim.nbafacts.model.Player
import com.ibrahim.nbafacts.model.PlayerMetaData
import com.ibrahim.nbafacts.service.NBAApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PlayersViewModel(application: Application) : BaseViewModel(application){

    private val nbaApiService = NBAApiService()
    // 'disposable' method makes it disposable the retrofit calls
    private val disposable = CompositeDisposable()

    val playerList = MutableLiveData<PlayerMetaData>()
    val playersList = MutableLiveData<List<Player>>()
    val playersLoading = MutableLiveData<Boolean>()
    val playersError = MutableLiveData<Boolean>()
    var page = MutableLiveData<Int>()

    fun getData(){
        playersLoading.value = true
        disposable.add(
            nbaApiService.getPlayers(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PlayerMetaData>(){
                    override fun onSuccess(t: PlayerMetaData) {
                        println("onsuccess")
                        playersList.value = t.player
                        playersError.value = false
                        playersLoading.value = false
                    }
                    override fun onError(e: Throwable) {
                        println("onerror")
                        playersLoading.value = false
                        playersError.value = true
                    }
                })
        )
    }

    fun changePage(){
        var default = page.value
        print("defaultafter:" + default.toString())
        default = default?.plus(1)
        print("default:" + default.toString())
        page.value = default
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}