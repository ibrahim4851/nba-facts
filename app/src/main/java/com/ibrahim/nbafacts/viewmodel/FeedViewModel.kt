package com.ibrahim.nbafacts.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ibrahim.nbafacts.model.FeedModel
import com.ibrahim.nbafacts.service.FeedApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel(application: Application): BaseViewModel(application) {

    private val feedApiService = FeedApiService()
    private val disposable = CompositeDisposable()

    val feed = MutableLiveData<List<FeedModel>>()
    val feedLoading = MutableLiveData<Boolean>()
    val feedError = MutableLiveData<Boolean>()

    fun getData(){
        disposable.add(
            feedApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<FeedModel>>(){
                    override fun onSuccess(t: List<FeedModel>) {
                        feed.value = t
                        feedError.value = false
                        feedLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        feedLoading.value = false
                        feedError.value = true
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}