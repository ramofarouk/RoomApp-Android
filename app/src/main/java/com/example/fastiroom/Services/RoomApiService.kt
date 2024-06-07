package com.example.fastiroom.services

import com.example.fastiroom.models.Room
import retrofit2.Call
import retrofit2.http.GET

interface RoomApiService {
    @GET("rooms")
    fun getAllRooms() : Call<List<Room>>
}