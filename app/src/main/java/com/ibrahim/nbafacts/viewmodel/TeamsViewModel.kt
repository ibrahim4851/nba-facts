package com.ibrahim.nbafacts.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ibrahim.nbafacts.service.NBAApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import com.ibrahim.nbafacts.model.*

class TeamsViewModel(application: Application): BaseViewModel(application) {

    private val nbaApiService = NBAApiService()
    private val disposable = CompositeDisposable()

    val teamsList = MutableLiveData<List<Team>>()
    val teamsLoading = MutableLiveData<Boolean>()
    val teamsError = MutableLiveData<Boolean>()
    var page = MutableLiveData<Int>()
    var totalPage = MutableLiveData<Int>()

    fun getData(){
        teamsLoading.value = true
        disposable.add(
            nbaApiService.getTeams(page.value.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TeamMetaData>(){
                    override fun onSuccess(t: TeamMetaData) {
                        teamsList.value = t.team
                        page.value = t.meta.currentPage.toInt()
                        totalPage.value = t.meta.totalPages.toInt()
                        teamsError.value = false
                        teamsLoading.value = false
                    }
                    override fun onError(e: Throwable) {
                        teamsLoading.value = false
                        teamsError.value = true
                        e.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}