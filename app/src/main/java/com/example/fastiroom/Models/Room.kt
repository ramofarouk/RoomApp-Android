package com.example.fastiroom.Models

data class Room(
    val id: Int,
    val label: String,
    val description: String,
    val prix: Double,
    val dimension: Double,
    val image: String,
    val disponibilite: Int
)