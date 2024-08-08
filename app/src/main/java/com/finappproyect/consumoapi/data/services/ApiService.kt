package com.finappproyect.consumoapi.data.services

import com.finappproyect.consumoapi.data.models.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/character/")
    suspend fun getCharacterByName(@Query("name") name: String): Response<Character>

}