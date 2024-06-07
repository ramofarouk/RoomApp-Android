package com.example.fastiroom.models


data class Reservation(
    val id: Int = 0,
    val label: String,
    val prix: Double,
    val nbreJours: Int,
    val dateReservation: String
)