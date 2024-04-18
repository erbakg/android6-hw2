package com.example.hw6_2.data.network

import com.example.hw6_2.BuildConfig
import com.example.hw6_2.data.models.Characters
import com.example.hw6_2.data.models.Episode
import com.example.hw6_2.data.models.Episodes
import com.example.hw6_2.data.models.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CharacterApi {

    @GET(BuildConfig.CHARACTER)
    suspend fun getCharactersByPage(
        @Query(BuildConfig.PAGE) page: String
    ): Response<Characters>

    @GET
    suspend fun getEpisode(@Url url: String): Response<Episode>

    @GET
    suspend fun getEpisodes(@Url url: String): Response<Episodes>

    @GET
    suspend fun getCharacter(@Url url: String): Response<Character>

}