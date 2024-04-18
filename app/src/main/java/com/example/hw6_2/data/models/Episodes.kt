package com.example.hw6_2.data.models

import com.google.gson.annotations.SerializedName

class Episodes : ArrayList<Episode>()

data class Episode(
    @SerializedName("air_date")
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)