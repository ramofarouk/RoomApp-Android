package com.example.fastiroom.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.fastiroom.models.Reservation
import com.example.fastiroom.R

class ReservationAdapter(private val context: Context, private val reservations : List<Reservation>) : BaseAdapter() {
    private val inflater : LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = reservations.size

    override fun getItem(position: Int): Reservation = reservations[position]

    override fun getItemId(position: Int): Long =  reservations[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = convertView ?: inflater.inflate(R.layout.reservation_item, parent, false)

        val labelTextView: TextView = view.findViewById(R.id.labelTextView)
        val joursTextView: TextView = view.findViewById(R.id.joursTextView)
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)

        val reservation = getItem(position)
        labelTextView.text = "${reservation.label} - ${reservation.prix} Є"
        joursTextView.text = "${reservation.nbreJours} jours"
        dateTextView.text = "${reservation.dateReservation}"

        return view
    }
}