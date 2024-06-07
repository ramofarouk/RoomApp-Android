package com.example.fastiroom.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fastiroom.R

class MainActivity : AppCompatActivity() {
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        sharedPreferences = getSharedPreferences("roomapp", Context.MODE_PRIVATE)
        checkUserConnection()

    }

    fun login(view: View){
        if(emailEditText.text.toString().isNotEmpty() && passwordEditText.text.toString().isNotEmpty()){
            sharedPreferences.edit().putBoolean("is_connected", true).apply()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Veuillez renseigner tous les champs",Toast.LENGTH_LONG).show()
        }
    }

    fun checkUserConnection(){
        val isConnected = sharedPreferences.getBoolean("is_connected", false)
        if(isConnected){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}