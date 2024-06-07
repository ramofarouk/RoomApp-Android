package com.example.fastiroom.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fastiroom.adapters.RoomAdapter
import com.example.fastiroom.models.Room
import com.example.fastiroom.R
import com.example.fastiroom.adapters.RoomAdapterNew
import com.example.fastiroom.view_models.RoomViewModel
import com.example.fastiroom.databinding.ActivityHomeBinding
import com.example.fastiroom.interfaces.OnItemClickListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity(), OnItemClickListener {

    var adapterRoom: RoomAdapterNew= RoomAdapterNew(this)

    private val roomViewModel: RoomViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = roomViewModel
        binding.lifecycleOwner = this


        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = adapterRoom
        }

        roomViewModel.rooms.observe(this, Observer {
            adapterRoom.setRooms(it)
            binding.recyclerView.visibility = View.VISIBLE
            binding.loading.visibility = View.GONE
        })

        roomViewModel.fetchRooms()
    }

    override fun onItemClick(room: Room) {
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


}