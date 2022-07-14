package com.ibrahim.nbafacts.service

import androidx.lifecycle.MutableLiveData
import com.ibrahim.nbafacts.model.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NBAApi {

    @GET("players")
    fun getPlayers(@Query("per_page") perPage: String, @Query("page") page: String):
            Single<PlayerMetaData>

    @GET("players")
    fun searchPlayer(@Query("per_page") perPage: String, @Query("search") searchQuery: String):
            Single<PlayerMetaData>

    @GET("games")
    fun getGames(@Query("per_page")perPage: String, @Query("page") page: String):
            Single<GameMetaData>

    @GET("teams")
    fun getTeams(@Query("per_page")perPage: String, @Query("page") page: String):
            Single<TeamMetaData>

    @GET("players/{id}")
    fun getPlayerById(@Path("id")playerId: String):
            Single<Player>
}