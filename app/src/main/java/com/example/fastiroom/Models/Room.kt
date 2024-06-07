package com.example.fastiroom.models

data class Room(
    val id: Int,
    val label: String,
    val description: String,
    val prix: Double,
    val dimension: Double,
    val image: String,
    val disponibilite: Int
){
    val prixString : String get() = prix.toString()
}