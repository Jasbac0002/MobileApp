package com.example.whatisthisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class AboutApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.about_app)

        val btnBack: Button = findViewById<Button>(R.id.btn_appinfo_back)

        btnBack.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish() // Optional: finish the current activity to remove it from the back stack
        }
    }
}