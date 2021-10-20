package com.ibrahim.nbafacts.model

import com.google.gson.annotations.SerializedName

data class GameMetaData(
    @SerializedName("data")
    val game: List<Game>,
    @SerializedName("meta")
    val meta: Meta
)