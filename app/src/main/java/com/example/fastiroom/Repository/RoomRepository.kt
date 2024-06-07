package com.example.fastiroom.repository

import com.example.fastiroom.helpers.ApiClient
import com.example.fastiroom.models.Room
import retrofit2.awaitResponse

class RoomRepository {
    suspend fun getRooms() : List<Room>?{
        val response = ApiClient.roomApiService.getAllRooms().awaitResponse()
        return if (response.isSuccessful){
            response.body()
        }else{
            mutableListOf()
        }
    }
}