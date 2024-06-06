package com.example.fastiroom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.ContentLoadingProgressBar
import com.example.fastiroom.Adapters.RoomAdapter
import com.example.fastiroom.Helpers.ApiClient
import com.example.fastiroom.Models.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var loadingProgressBar: ProgressBar

    lateinit var btnViewReservation: FloatingActionButton

    val roomsList: MutableList<Room> = mutableListOf()
    lateinit var adapterRoom: RoomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        getRooms()

        listView = findViewById(R.id.listView)
        btnViewReservation = findViewById(R.id.btnViewReservation)
        loadingProgressBar = findViewById(R.id.loading)
        adapterRoom = RoomAdapter(this, roomsList)
        listView.adapter = adapterRoom


        listView.setOnItemClickListener { parent, view, position, id ->
            val room: Room = roomsList[position]
            val intent = Intent(this, RoomDetailsActivity::class.java)
            Log.e("Details_room", room.toString())
            intent.putExtra("id", room.id)
            intent.putExtra("label", room.label)
            intent.putExtra("description", room.description)
            intent.putExtra("prix", room.prix)
            intent.putExtra("dimension", room.dimension)
            intent.putExtra("disponible", room.disponibilite)
            intent.putExtra("image", room.image)
            startActivity(intent)
        }

        btnViewReservation.setOnClickListener {
            val intent = Intent(this, ListReservationActivity::class.java)
            startActivity(intent)
        }

        //deleteDatabase()
    }

    fun getRooms() {
        val callGetRooms = ApiClient.roomApiService.getAllRooms()
        callGetRooms.enqueue(object : Callback<List<Room>> {
            override fun onResponse(
                callListRoom: Call<List<Room>>,
                response: Response<List<Room>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { rooms ->
                        roomsList.addAll(rooms)
                        adapterRoom.notifyDataSetChanged()
                        loadingProgressBar.visibility = View.GONE
                        listView.visibility = View.VISIBLE
                    }
                } else {
                    Toast.makeText(
                        this@HomeActivity,
                        "Erreur survenue lors de la recupération des données!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(p0: Call<List<Room>>, p1: Throwable) {
                Toast.makeText(this@HomeActivity, "Serveur indisponible!", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun deleteDatabase() {
        val context = applicationContext
        val status = context.deleteDatabase("roomapp.db")
        Log.e("SQLITE", status.toString())
    }
}