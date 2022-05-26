package com.ibrahim.nbafacts.service

import androidx.lifecycle.MutableLiveData
import com.ibrahim.nbafacts.model.GameMetaData
import com.ibrahim.nbafacts.model.MetaData
import com.ibrahim.nbafacts.model.PlayerMetaData
import com.ibrahim.nbafacts.model.TeamMetaData
import io.reactivex.Single
import retrofit2.http.GET
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
}