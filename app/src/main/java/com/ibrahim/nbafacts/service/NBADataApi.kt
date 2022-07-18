package com.ibrahim.nbafacts.service

import com.ibrahim.nbafacts.model.nbadata.NbaData
import io.reactivex.Single
import retrofit2.http.GET

interface NBADataApi {

    @GET("/players.json")
    fun getAllPlayers():Single<NbaData>

}