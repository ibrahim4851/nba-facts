package com.ibrahim.nbafacts.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Game(
    @SerializedName("id")
    val id: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("home_team")
    val homeTeam: Team,

    @SerializedName("visitor_team")
    val awayTeam: Team,

    @SerializedName("home_team_score")
    val homeScore: String,

    @SerializedName("visitor_team_score")
    val awayScore: String,

    @SerializedName("period")
    val period: String,

    @SerializedName("postseason")
    val postSeason: Boolean,

    @SerializedName("season")
    val season: String,

    @SerializedName("status")
    val status: String
)


/*
{
            "id": 32881,
            "date": "2017-11-06T00:00:00.000Z",
            "home_team": {
                "id": 1,
                "abbreviation": "ATL",
                "city": "Atlanta",
                "conference": "East",
                "division": "Southeast",
                "full_name": "Atlanta Hawks",
                "name": "Hawks"
            },
            "home_team_score": 107,
            "period": 4,
            "postseason": false,
            "season": 2017,
            "status": "Final",
            "time": " ",
            "visitor_team": {
                "id": 2,
                "abbreviation": "BOS",
                "city": "Boston",
                "conference": "East",
                "division": "Atlantic",
                "full_name": "Boston Celtics",
                "name": "Celtics"
            },
            "visitor_team_score": 110
        }
 */