package com.ibrahim.nbafacts.service

import com.ibrahim.nbafacts.model.FeedModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FeedApiService {

    private val BASE_URL = "https://raw.githubusercontent.com/ibrahim4851/nba-facts-db/main/"

    private val api = Retrofit.Builder().
    baseUrl(BASE_URL).
    addConverterFactory(GsonConverterFactory.create()).
    addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
    build().
    create(FeedApi::class.java)

    fun getData(): Single<List<FeedModel>>{
        return api.getFeed()
    }

}