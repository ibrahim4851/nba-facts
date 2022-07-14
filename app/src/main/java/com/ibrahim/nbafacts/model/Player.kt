package com.ibrahim.nbafacts.model

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("id")
    val id: String,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("height_feet")
    val heightFeet: Int,

    @SerializedName("height_inches")
    val heightInches: Int,

    @SerializedName("position")
    val position: String,

    @SerializedName("team")
    val team: Team,

    @SerializedName("weight_pounds")
    val weightPounds: Int
)


/*
{
    "data": [
        {
            "id": 140,
            "first_name": "Kevin",
            "height_feet": 6,
            "height_inches": 9,
            "last_name": "Durant",
            "position": "F",
            "team": {
                "id": 3,
                "abbreviation": "BKN",
                "city": "Brooklyn",
                "conference": "East",
                "division": "Atlantic",
                "full_name": "Brooklyn Nets",
                "name": "Nets"
            },
            "weight_pounds": 240
        }
    ],
    "meta": {
        "total_pages": 1,
        "current_page": 1,
        "next_page": null,
        "per_page": 2,
        "total_count": 1
    }
}
 */