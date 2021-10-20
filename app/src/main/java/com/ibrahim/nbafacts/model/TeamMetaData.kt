package com.ibrahim.nbafacts.model

import com.google.gson.annotations.SerializedName

data class TeamMetaData(
    @SerializedName("data")
    val team: List<Team>,
    @SerializedName("meta")
    val meta: Meta
)
