package com.ibrahim.nbafacts.model

import com.google.gson.annotations.SerializedName

data class PlayerMetaData(
    @SerializedName("data")
    val player: List<Player>,
    @SerializedName("meta")
    val meta: Meta
)
