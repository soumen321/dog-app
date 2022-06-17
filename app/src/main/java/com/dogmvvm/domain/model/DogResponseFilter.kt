package com.dogmvvm.domain.model

import org.json.JSONObject

interface DogResponseFilter {

    companion object {
        fun filterDogResponse(jsonObject: JSONObject): Dog {
            val imageUrl = jsonObject.optString("message")
            return Dog(imageUrl)
        }
    }
}