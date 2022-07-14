package com.ibrahim.nbafacts.service

import com.ibrahim.nbafacts.model.*
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NBAApiService {

    private val BASE_URL = "https://balldontlie.io/api/v1/"

    private val api = Retrofit.Builder().
    baseUrl(BASE_URL).
    addConverterFactory(GsonConverterFactory.create()).
    addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
    build().
    create(NBAApi::class.java)

    fun getPlayers(page: String): Single<PlayerMetaData>{
        return api.getPlayers("15", page)
    }

    fun searchPlayer(name: String): Single<PlayerMetaData>{
        return api.searchPlayer("15", name)
    }

    fun getGames(page: String): Single<GameMetaData>{
        return api.getGames("15", page)
    }

    fun getTeams(page: String): Single<TeamMetaData>{
        return api.getTeams("15", page)
    }

    fun getSinglePlayer(playerId: String): Single<Player>{
        return api.getPlayerById(playerId)
    }

}