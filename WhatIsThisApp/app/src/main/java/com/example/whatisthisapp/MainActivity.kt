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
import android.content.Context
import android.view.inputmethod.InputMethodManager

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)

        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        //Declaring variables
         val btnContinue: Button = findViewById(R.id.btn_continue)
         val etName = findViewById<EditText>(R.id.et_name)

        val rootView = findViewById<View>(android.R.id.content)
        rootView.setOnClickListener {
            hideKeyboard()
        }

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        btnContinue.setOnClickListener {
            val name = etName.text.toString().trim() // Trim the input to remove leading/trailing whitespace

            if (name.isEmpty()) {
                Toast.makeText(this, "Your name Please :)", Toast.LENGTH_SHORT).show()
            } else {
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

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        val currentFocusedView = currentFocus
        if (currentFocusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }


}