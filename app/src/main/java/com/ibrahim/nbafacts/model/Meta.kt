package com.ibrahim.nbafacts.model

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("total_pages")
    val totalPages: String,

    @SerializedName("total_count")
    val totalCount: String,

    @SerializedName("current_page")
    val currentPage: String,

    @SerializedName("next_page")
    val nextPage: String,

    @SerializedName("per_page")
    val perPage: String
)
