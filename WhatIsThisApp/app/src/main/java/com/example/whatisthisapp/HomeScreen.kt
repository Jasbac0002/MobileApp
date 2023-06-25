package com.example.whatisthisapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class HomeScreen : AppCompatActivity() {
    private lateinit var tvName: TextView
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.home_screen)


        //Declaration
        tvName = findViewById<TextView>(R.id.tv_name)
        //val name = intent.getStringExtra("name")
        val btnAppInfo: Button = findViewById<Button>(R.id.btn_app_info)
        val btnQuizSettings: Button = findViewById<Button>(R.id.btn_quiz_settings)
        val btnAppDeveloper: Button = findViewById<Button>(R.id.btn_app_developer)


        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        // Retrieve the user name from SharedPreferences
        val userName = sharedPreferences.getString("userName", "")

        // Display the user name in the TextView
        tvName.text = "$userName!"

        btnAppInfo.setOnClickListener{
            val intent = Intent(this, AboutApp::class.java)
            startActivity(intent)
            finish()
        }
        btnQuizSettings.setOnClickListener{
            val intent = Intent(this, QuizSettings::class.java)
            startActivity(intent)
            finish()
        }
        btnAppDeveloper.setOnClickListener{
            val intent = Intent(this, AboutDeveloper::class.java)
            startActivity(intent)
            finish()
        }

    }

}