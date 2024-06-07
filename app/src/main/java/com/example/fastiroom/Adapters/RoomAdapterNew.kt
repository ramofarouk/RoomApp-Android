package com.example.fastiroom.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fastiroom.R
import com.example.fastiroom.databinding.RoomItemBinding
import com.example.fastiroom.interfaces.OnItemClickListener
import com.example.fastiroom.models.Room

class RoomAdapterNew(private val listener: OnItemClickListener) : RecyclerView.Adapter<RoomAdapterNew.RoomViewHolder>(){
    private var roomList : MutableList<Room> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding : RoomItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.room_item,parent, false)
        return RoomViewHolder(binding)
    }

    override fun getItemCount(): Int  = roomList.size

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(roomList[position], listener = listener)
    }

    fun setRooms(rooms : List<Room>){
        roomList.addAll(rooms)
        notifyDataSetChanged()
    }

    class RoomViewHolder(private val binding: RoomItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(room: Room, listener : OnItemClickListener){
            binding.room = room
            binding.executePendingBindings()
            itemView.setOnClickListener { listener.onItemClick(room) }
        }
    }
}