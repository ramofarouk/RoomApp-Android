package com.example.fastiroom.Services

import com.example.fastiroom.Models.Room
import retrofit2.Call
import retrofit2.http.GET

interface RoomApiService {
    @GET("rooms")
    fun getAllRooms() : Call<List<Room>>
}