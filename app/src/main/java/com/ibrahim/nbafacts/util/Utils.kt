package com.ibrahim.nbafacts.util

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.ibrahim.nbafacts.R
import com.ibrahim.nbafacts.model.PlayerMetaData
import com.ibrahim.nbafacts.service.NBAApiService
import com.ibrahim.nbafacts.viewmodel.BaseViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .centerCrop()
        .into(this)

}

fun placeHolderProgressBar(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        centerRadius = 40f
        strokeWidth = 8f
        start()
    }
}
/*
fun <T> getJsonData(param: (String) -> Single<T>):Single<T>{
    return NBAApiService::getPlayers
}

fun <T> BaseViewModel.getDataUtil( functionParameter: T, disposable: CompositeDisposable,
                     apiFunction: (String) -> Single<T>){
    //dataLoading.value = true
    disposable.add(
        apiFunction(functionParameter.toString())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<T>(){
                override fun onSuccess(t: T) {
                    Log.i("utildata", t.toString())
                }

                override fun onError(e: Throwable) {
                    //dataLoading.value = true
                }

            })
    )
}*/

//below annotation makes it enable to use that function in xml files
@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url: String?){
    view.downloadFromUrl(url, placeHolderProgressBar(view.context))
}