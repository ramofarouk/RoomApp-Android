package com.example.fastiroom.views

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fastiroom.models.Reservation
import com.example.fastiroom.R
import com.example.fastiroom.repository.ReservationRepository
import java.util.Calendar

class ReservationActivity : AppCompatActivity() {
    lateinit var joursEditText: EditText
    lateinit var dateEditText: EditText
    lateinit var buttonReservation: Button

    lateinit var reservationRepository: ReservationRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reservation)

        reservationRepository = ReservationRepository(this)

        val intent = intent
        val prix : Double = intent.getDoubleExtra("prix", 0.0)
        val label : String? = intent.getStringExtra("label")

        joursEditText = findViewById(R.id.joursEditText)
        dateEditText = findViewById(R.id.dateEditText)
        buttonReservation = findViewById(R.id.buttonReservation)

        dateEditText.setOnClickListener {

            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(
                this, { view, currentYear, currentMonth, currentDay ->
                    dateEditText.setText("$currentYear-${currentMonth+1}-$currentDay")
                },year,month,day
            )
            datePickerDialog.show()
        }
        buttonReservation.setOnClickListener {
            val nbreJours = joursEditText.text.toString()
            val dateReservation = dateEditText.text.toString()
            if(nbreJours.isNotEmpty() && dateReservation.isNotEmpty()){
                Log.e("DATA","${label.toString()}-$prix-${nbreJours.toInt()}-$dateReservation")
                val status = reservationRepository.addReservation(Reservation(
                    label = label.toString(),
                    prix = prix,
                    nbreJours = nbreJours.toInt(),
                    dateReservation = dateReservation
                ))
                if(status.toInt() != -1){
                    joursEditText.setText("")
                    dateEditText.setText("")
                    Toast.makeText(this, "Réservation effectué avec succès", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Erreur survenue lors de la réservation", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show()
            }
        }
    }
}