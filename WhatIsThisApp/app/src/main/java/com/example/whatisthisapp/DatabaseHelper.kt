package com.example.whatisthisapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//This is the Data Logic Tier (3-Tier Architecture)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "names.db"
        private const val DATABASE_VERSION = 1

        // Table Name and Columns
        private const val TABLE_NAME = "names_table"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertName(name: String) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, name)
        db.insert(TABLE_NAME, null, contentValues)
    }

    @SuppressLint("Range")
    fun getAllNames(): ArrayList<String> {
        val namesList = ArrayList<String>()
        val db = readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        cursor?.let {
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                namesList.add(name)
            }
            cursor.close()
        }

        return namesList
    }

    fun clearAllNames() {
        val db = writableDatabase
        db.delete(TABLE_NAME, null, null)
    }
}
