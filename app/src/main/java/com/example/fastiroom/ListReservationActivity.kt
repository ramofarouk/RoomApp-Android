package com.example.fastiroom

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fastiroom.Adapters.ReservationAdapter
import com.example.fastiroom.Adapters.RoomAdapter
import com.example.fastiroom.Models.Reservation
import com.example.fastiroom.Models.Room
import com.example.fastiroom.Repository.ReservationRepository

class ListReservationActivity : AppCompatActivity() {
    lateinit var listView : ListView
    lateinit var loadingProgressBar: ProgressBar

    var reservationsList : MutableList<Reservation> = mutableListOf()
    lateinit var adapterReservation : ReservationAdapter

    lateinit var reservationRepository: ReservationRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list_reservation)

        reservationRepository = ReservationRepository(this)


        listView = findViewById(R.id.listView)
        loadingProgressBar = findViewById(R.id.loading)

        getReservations()
    }

    fun getReservations(){
        reservationsList = reservationRepository.getAllReservations()
        adapterReservation = ReservationAdapter(this, reservationsList)
        listView.adapter =  adapterReservation
        loadingProgressBar.visibility = View.GONE
        listView.visibility = View.VISIBLE
        Log.e("ROOM_LIST_SIZE", reservationsList[0].id.toString())
    }
}