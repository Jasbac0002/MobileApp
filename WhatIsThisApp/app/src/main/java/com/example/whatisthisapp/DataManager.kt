package com.example.whatisthisapp

import android.content.Context


//This is the Business Logic Tier (3-Tier Architecture)
class DataManager(context: Context) {

    private val databaseHelper: DatabaseHelper = DatabaseHelper(context)

    fun insertName(name: String) {
        databaseHelper.insertName(name)
    }

    fun getAllNames(): ArrayList<String> {
        return databaseHelper.getAllNames()
    }

    fun clearAllNames() {
        databaseHelper.clearAllNames()
    }


}
