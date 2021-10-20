package com.ibrahim.nbafacts.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("id")
    val id: String,

    @SerializedName("abbreviation")
    val abbreviation: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("conference")
    val conference: String,

    @SerializedName("divison")
    val division: String,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("name")
    val name: String
)

/*
{
    "id": 4,
    "abbreviation": "CHA",
    "city": "Charlotte",
    "conference": "East",
    "division": "Southeast",
    "full_name": "Charlotte Hornets",
    "name": "Hornets"
}
 */