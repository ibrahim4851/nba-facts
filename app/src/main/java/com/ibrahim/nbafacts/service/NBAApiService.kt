package com.ibrahim.nbafacts.service

import androidx.lifecycle.MutableLiveData
import com.ibrahim.nbafacts.model.GameMetaData
import com.ibrahim.nbafacts.model.MetaData
import com.ibrahim.nbafacts.model.PlayerMetaData
import com.ibrahim.nbafacts.model.TeamMetaData
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

    fun getPlayers(page: MutableLiveData<Int>): Single<PlayerMetaData>{
        return api.getPlayers("9", page)
    }

    fun getGames(page: MutableLiveData<Int>): Single<GameMetaData>{
        return api.getGames("9", page)
    }

    fun getTeams(page: MutableLiveData<Int>): Single<TeamMetaData>{
        return api.getTeams("9", page)
    }

}