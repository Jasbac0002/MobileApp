package com.example.whatisthisapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


class HomeScreen : AppCompatActivity() {
    private lateinit var tvName: TextView
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var imageView: ImageView


    companion object {
        val IMAGE_REQUEST_CODE = 100;
    }


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
        imageView = findViewById(R.id.iv_avatar)
        tvName.setOnLongClickListener(longClickListener)


        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        // Retrieve the user name from SharedPreferences
        val userName = sharedPreferences.getString("userName", "")

        // Display the user name in the TextView
        tvName.text = "$userName!"

        btnAppInfo.setOnClickListener {
            val intent = Intent(this, AboutApp::class.java)
            startActivity(intent)
        }
        btnQuizSettings.setOnClickListener {
            val intent = Intent(this, QuizSettings::class.java)
            startActivity(intent)
        }
        btnAppDeveloper.setOnClickListener {
            val intent = Intent(this, AboutDeveloper::class.java)
            startActivity(intent)
        }

        imageView.setOnClickListener {
            pickImageGallery()
        }

    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            imageView.setImageURI(data?.data)
        }
    }
    private val longClickListener = View.OnLongClickListener {
        if (it.id == R.id.tv_name) {
            showToast("Hello, LongClick Pressed!")
            true
        } else {
            false
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
