package com.example.fastiroom.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.fastiroom.models.Room
import com.example.fastiroom.R

class RoomAdapter(private val context: Context,private val rooms : MutableList<Room>) : BaseAdapter() {
    private val inflater : LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = rooms.size

    override fun getItem(position: Int): Room  = rooms[position]

    override fun getItemId(position: Int): Long =  rooms[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = convertView ?: inflater.inflate(R.layout.room_item, parent, false)

        val imageView:ImageView = view.findViewById(R.id.imageView)
        val labelTextView:TextView = view.findViewById(R.id.labelTextView)
        val prixTextView:TextView = view.findViewById(R.id.prixTextView)

        val room = getItem(position)
        labelTextView.text = room.label
        prixTextView.text = "${room.prix} Є"

        Glide.with(view).load("https://kofhotel.kofcorporation.com/old${room.image}").into(imageView)

        return view
    }
}