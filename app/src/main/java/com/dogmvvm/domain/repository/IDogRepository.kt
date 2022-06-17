package com.dogmvvm.domain.repository

import com.google.gson.JsonElement
import retrofit2.Response

interface IDogRepository {
    suspend fun getDog() : Response<JsonElement>
}