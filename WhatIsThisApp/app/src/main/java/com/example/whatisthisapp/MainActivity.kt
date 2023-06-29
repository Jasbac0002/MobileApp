package com.example.whatisthisapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Splash Screen
        Thread.sleep(2000)
        //installSplashScreen()

       // setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)

        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        //Declaring variables
         val btnContinue: Button = findViewById<Button>(R.id.btn_continue)
         val etName = findViewById<EditText>(R.id.et_name)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        btnContinue.setOnClickListener {
            val name = etName.text.toString()

            // Save the user name in SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("userName", name)
            editor.apply()

            // Start the WelcomeActivity
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

    }


}