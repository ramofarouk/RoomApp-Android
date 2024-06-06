package com.example.fastiroom.Models

import java.sql.Date


data class Reservation(
    val id: Int = 0,
    val label: String,
    val prix: Double,
    val nbreJours: Int,
    val dateReservation: String
)