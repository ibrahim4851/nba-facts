package com.ibrahim.nbafacts.service

import com.ibrahim.nbafacts.model.*
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NBADataApiService {

    private val BASE_URL = "https://data.nba.net/data/10s/prod/v1/2020/"

    private val api = Retrofit.Builder().
    baseUrl(BASE_URL).
    addConverterFactory(GsonConverterFactory.create()).
    addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
    build().
    create(NBADataApi::class.java)

}