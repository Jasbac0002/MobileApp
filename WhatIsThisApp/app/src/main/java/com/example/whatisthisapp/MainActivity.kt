package com.example.whatisthisapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.os.Handler

class MainActivity : AppCompatActivity() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)


        //Declaring variables
        val btnContinue = findViewById<Button>(R.id.btn_continue)
        val etName = findViewById<EditText>(R.id.et_name)

        //---------------------------------------------------

        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btnContinue.setOnClickListener {

            if (etName.text.toString().isEmpty()) {
                Toast.makeText(this,"Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                val intent: Intent = Intent(this, QuizQuestionsActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }


}