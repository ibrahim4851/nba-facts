package com.ibrahim.nbafacts.service

import com.ibrahim.nbafacts.model.FeedModel
import io.reactivex.Single
import retrofit2.http.GET

interface FeedApi {

    @GET("db.json")
    fun getFeed(): Single<List<FeedModel>>

}