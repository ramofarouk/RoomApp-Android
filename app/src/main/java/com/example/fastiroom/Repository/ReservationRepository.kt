package com.example.fastiroom.Repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException
import android.util.Log
import com.example.fastiroom.Helpers.DatabaseHelper
import com.example.fastiroom.Models.Reservation
import java.sql.SQLException

class ReservationRepository(context: Context) {
    object CONTACT_TABLE{
        const val TABLE_NAME = "rooms"
        const val COL_ID  = "id"
        const val COL_NOM_CHAMBRE  = "nom_chambre"
        const val COL_PRIX = "prix"
        const val COL_NBRE_JOURS = "nbre_jours"
        const val COL_DATE_RESERVATION = "date_reservation"
    }

    val dbHelper = DatabaseHelper(context)

    fun addReservation(reservation: Reservation): Long {
        try {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(CONTACT_TABLE.COL_NOM_CHAMBRE, reservation.label)
                put(CONTACT_TABLE.COL_PRIX, reservation.prix)
                put(CONTACT_TABLE.COL_NBRE_JOURS, reservation.nbreJours)
                put(CONTACT_TABLE.COL_DATE_RESERVATION, reservation.dateReservation)
            }

            val status = db.insert(CONTACT_TABLE.TABLE_NAME, null, values)
            db.close()
            return status
        }catch(sqlEception : Exception){
            Log.e("ERROR_DB", sqlEception.toString())
            return -1
        }

    }

    fun getAllReservations(): MutableList<Reservation> {
        val db = dbHelper.readableDatabase
        val reservations: MutableList<Reservation> = mutableListOf<Reservation>()
        try{
            val cursor = db.query(
                CONTACT_TABLE.TABLE_NAME, null, null, null, null, null, null, null
            )

            with(cursor) {
                while (moveToNext()) {
                    val id = getInt(getColumnIndexOrThrow(CONTACT_TABLE.COL_ID))
                    val nomChambre = getString(getColumnIndexOrThrow(CONTACT_TABLE.COL_NOM_CHAMBRE))
                    val prix = getDouble(getColumnIndexOrThrow(CONTACT_TABLE.COL_PRIX))
                    val nbreJours = getInt(getColumnIndexOrThrow(CONTACT_TABLE.COL_NBRE_JOURS))
                    val dateReservation = getString(getColumnIndexOrThrow(CONTACT_TABLE.COL_DATE_RESERVATION))

                    reservations.add(
                        Reservation(
                            id = id,
                            label = nomChambre,
                            prix = prix,
                            nbreJours = nbreJours,
                            dateReservation = dateReservation
                        )
                    )
                }
            }
            cursor.close()
        }catch (sqlException : SQLiteException){
            Log.e("ERROR_DB", sqlException.toString())
        }
        return reservations

    }

    fun updateReservation(reservation: Reservation): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(CONTACT_TABLE.COL_NOM_CHAMBRE, reservation.label)
            put(CONTACT_TABLE.COL_PRIX, reservation.prix)
            put(CONTACT_TABLE.COL_NBRE_JOURS, reservation.nbreJours)
            put(CONTACT_TABLE.COL_DATE_RESERVATION, reservation.dateReservation)
        }

        val status = db.update(CONTACT_TABLE.TABLE_NAME, values,"${CONTACT_TABLE.COL_ID}=?", arrayOf(reservation.id.toString()))
        db.close()
        return status
    }

    fun getSingleReservation(id: Int): Reservation? {
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            CONTACT_TABLE.TABLE_NAME, null, "${CONTACT_TABLE.COL_ID}=?", arrayOf(id.toString()), null, null, null, null
        )

        var reservation: Reservation? = null

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(CONTACT_TABLE.COL_ID))
                val nomChambre = getString(getColumnIndexOrThrow(CONTACT_TABLE.COL_NOM_CHAMBRE))
                val prix = getDouble(getColumnIndexOrThrow(CONTACT_TABLE.COL_PRIX))
                val nbreJours = getInt(getColumnIndexOrThrow(CONTACT_TABLE.COL_NBRE_JOURS))
                val dateReservation = getString(getColumnIndexOrThrow(CONTACT_TABLE.COL_DATE_RESERVATION))

                reservation =  Reservation(
                    id = id,
                    label = nomChambre,
                    prix = prix,
                    nbreJours = nbreJours,
                    dateReservation = dateReservation
                )
            }
        }

        cursor.close()
        return reservation
    }

    fun deleteReservation(id: Int): Int {
        val db = dbHelper.writableDatabase
        val status = db.delete(CONTACT_TABLE.TABLE_NAME,"${CONTACT_TABLE.COL_ID}=?", arrayOf(id.toString()))
        db.close()
        return status
    }
}