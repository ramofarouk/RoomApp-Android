package com.example.fastiroom.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastiroom.models.Room
import com.example.fastiroom.repository.RoomRepository
import kotlinx.coroutines.launch

class RoomViewModel: ViewModel() {
    private val roomRepository = RoomRepository()
    private val _rooms  = MutableLiveData<List<Room>>()
    val rooms : LiveData<List<Room>> get() = _rooms

    fun fetchRooms(){
        viewModelScope.launch {
            val rooms = roomRepository.getRooms()
            _rooms.postValue(rooms!!)
        }
    }

}