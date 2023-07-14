package com.example.whatisthisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Timer
import java.util.TimerTask

class SplashActivity : AppCompatActivity() {

    private val splashScreenDuration: Long = 5000 //Set Timer for  5 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        // Delayed redirection to MainActivity after splashScreenDuration
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                navigateToMainActivity()
            }
        }, splashScreenDuration)
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}