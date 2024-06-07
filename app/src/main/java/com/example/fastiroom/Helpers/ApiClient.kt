package com.example.fastiroom.helpers

import com.example.fastiroom.services.RoomApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URL = "https://kofhotel.kofcorporation.com/old/api/"

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val roomApiService : RoomApiService by lazy {
        retrofit.create(RoomApiService::class.java)
    }
}