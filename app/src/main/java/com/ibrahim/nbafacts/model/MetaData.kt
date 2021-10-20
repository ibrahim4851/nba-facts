package com.ibrahim.nbafacts.model

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class MetaData(
    @SerializedName("data")
    val dataClass: JsonArray,
    @SerializedName("meta")
    val meta: Meta
)
