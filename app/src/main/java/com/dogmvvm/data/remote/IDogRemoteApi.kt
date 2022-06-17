package com.dogmvvm.data.remote

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET

interface IDogRemoteApi {

    @GET("/api/breeds/image/random")
    suspend fun getDog() : Response<JsonElement>
}