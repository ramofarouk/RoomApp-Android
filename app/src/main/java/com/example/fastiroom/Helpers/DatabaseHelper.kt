package com.example.fastiroom.Helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DATABASE_NAME = "roomapp.db"
private const val DATABASE_VERSION = 1

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    object CONTACT_TABLE{
        const val TABLE_NAME = "rooms"
        const val COL_ID  = "id"
        const val COL_NOM_CHAMBRE  = "nom_chambre"
        const val COL_PRIX = "prix"
        const val COL_NBRE_JOURS = "nbre_jours"
        const val COL_DATE_RESERVATION = "date_reservation"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createContactTable = """
            CREATE TABLE ${CONTACT_TABLE.TABLE_NAME} (
                ${CONTACT_TABLE.COL_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${CONTACT_TABLE.COL_NOM_CHAMBRE} TEXT NOT NULL,
                ${CONTACT_TABLE.COL_PRIX} DOUBLE NOT NULL,
                ${CONTACT_TABLE.COL_NBRE_JOURS} INTEGER NOT NULL,
                ${CONTACT_TABLE.COL_DATE_RESERVATION} DATE NOT NULL
            )
        """.trimIndent()
        db?.execSQL(createContactTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}