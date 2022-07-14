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

class PlayerDetailsViewModel(application: Application): BaseViewModel(application) {

    private val nbaApiService = NBAApiService()
    private val disposable = CompositeDisposable()
    val player = MutableLiveData<Player>()
    val playerId = MutableLiveData<String>()

    fun getData() {
        //playersLoading.value = true
        disposable.add(
            nbaApiService.getSinglePlayer(playerId.value.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Player>() {
                    override fun onSuccess(t: Player) {
                        player.value = t
                    }

                    override fun onError(e: Throwable) {
                        //playersLoading.value = false
                        //playersError.value = true
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