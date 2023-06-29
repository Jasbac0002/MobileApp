package com.example.whatisthisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup

class QuizSettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        setContentView(R.layout.quiz_settings)

        val btnBack: Button = findViewById<Button>(R.id.btn_quizsettings_back)
        val btnBeginQuiz: Button = findViewById<Button>(R.id.btn_BeginQuiz)


        btnBeginQuiz.setOnClickListener {
            val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
            val selectedOption = when (radioGroup.checkedRadioButtonId) {
                R.id.rbButton1 -> "Fruits"
                R.id.rbButton2 -> "Animals"
                else -> "None" // Handle the default case if no option is selected
            }

            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("selectedOption", selectedOption)
            startActivity(intent)
        }


        btnBack.setOnClickListener{
            onBackPressed()
        }
    }
}