package com.example.whatisthisapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Splash Screen
        Thread.sleep(3000)
        installSplashScreen()

       // setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)

        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        //Declaring variables
         val btnContinue: Button = findViewById<Button>(R.id.btn_continue)
         val etName = findViewById<EditText>(R.id.et_name)

        btnContinue.setOnClickListener {

            if (etName.text.toString().isEmpty()) {
                Toast.makeText(this,"Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                val intent: Intent = Intent(this, QuizSettings::class.java)
                startActivity(intent)
                finish()
            }
        }

    }


}