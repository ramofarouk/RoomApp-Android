package com.example.fastiroom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class RoomDetailsActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var labelTextView: TextView
    lateinit var descriptionTextView: TextView
    lateinit var prixTextView: TextView
    lateinit var dimensionTextView: TextView
    lateinit var disponibleTextView : TextView

    lateinit var buttonReservation : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_room_details)

        imageView = findViewById(R.id.imageView)
        labelTextView = findViewById(R.id.labelTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        prixTextView = findViewById(R.id.prixTextView)
        dimensionTextView = findViewById(R.id.dimensionTextView)
        disponibleTextView = findViewById(R.id.disponibleTextView)
        buttonReservation = findViewById(R.id.buttonReservation)

        val intent = intent
        val prix : Double = intent.getDoubleExtra("prix", 0.0)
        val label : String? = intent.getStringExtra("label")

        labelTextView.text = label
        descriptionTextView.text = intent.getStringExtra("description")
        prixTextView.text = "$prix Є"
        dimensionTextView.text = intent.getDoubleExtra("dimension",0.0).toString()
        disponibleTextView.text = if(intent.getIntExtra("disponible",0) == 1) { "Disponible" } else { "Indisponible" }

        Glide.with(this).load("https://kofhotel.kofcorporation.com/old${intent.getStringExtra("image")}").into(imageView)

        buttonReservation.setOnClickListener {
            val intentToReservation = Intent(this, ReservationActivity::class.java)
            intentToReservation.putExtra("label",label)
            intentToReservation.putExtra("prix",prix)
            startActivity(intentToReservation)
        }
    }
}