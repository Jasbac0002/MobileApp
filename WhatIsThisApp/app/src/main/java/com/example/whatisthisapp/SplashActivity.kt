package com.example.whatisthisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.Timer
import java.util.TimerTask

class SplashActivity : AppCompatActivity() {

    private val splashScreenDuration: Long = 2500 //Set Timer for  2.5 seconds
    private lateinit var textView: TextView
    private lateinit var dataManager: DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)


        // Delayed redirection to MainActivity after splashScreenDuration
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                navigateToMainActivity()
            }
        }, splashScreenDuration)

        textView = findViewById(R.id.tvDevelopers)
        dataManager = DataManager(this)


        //Apply Exception Handling
        try {
            // Clear all names from the database
            dataManager.clearAllNames()

            // Insert Names of the developers
            dataManager.insertName("JASPER BACANI")
            dataManager.insertName("WORAPAT LERTBUNCHADEE")
            dataManager.insertName("KUMAR TAMANG DEEPAK")

            // Query all names and display them in the TextView
            val allNames = dataManager.getAllNames()
            textView.text = "Developed by: \n\n" + allNames.joinToString("\n")
        } catch (e: Exception) {
            // Handle any specific exception that might occur during the database operations
            // For example, you could display an error message or log the exception.
            e.printStackTrace()
        }


    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}