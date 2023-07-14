package com.example.whatisthisapp


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class ResultsActivity : AppCompatActivity() {

    lateinit var correctAns:TextView
    lateinit var totalAns:TextView
    lateinit var performance:TextView
    lateinit var output:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results_activity)

        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        supportActionBar?.hide()

        correctAns=findViewById(R.id.correctAns)
        totalAns=findViewById(R.id.totalAns)
        performance=findViewById(R.id.performance)
        output=findViewById(R.id.output)
        output=findViewById(R.id.output)

        val intent = intent
        val correctAnsNo=intent.getStringExtra("correct")
        val totalAnsNo=intent.getStringExtra("total")
        correctAns.text=correctAnsNo
        totalAns.text=totalAnsNo

        val percentage= (correctAnsNo?.toFloat()?.div(totalAnsNo?.toFloat()!!))?.times(100)

        if (percentage != null) when {
            50 <= percentage && percentage <= 99 -> {

                performance.text = "You're Getting There!"
                output.background = resources.getDrawable(R.drawable.option_bg)


            }

            percentage >= 100 -> {
                performance.text = "Perfect! Your Awesome!"
                output.background = resources.getDrawable(R.drawable.right_bg)
            }

            percentage < 50 -> {
                performance.text = "Just Need More Practice :)"
                output.background = resources.getDrawable(R.drawable.wrong_bg)
            }
        }

        val btnPlayAgain: Button = findViewById<Button>(R.id.btnPlayAgain)
        val btnExit: Button = findViewById<Button>(R.id.btnExitGame)

        btnPlayAgain.setOnClickListener {
            val intent = Intent(this, QuizSettings::class.java)
            startActivity(intent)
        }
        btnExit.setOnClickListener{

            Toast.makeText(this, "Thank You! For Playing my App!\n" + "-Jasper Bacani", Toast.LENGTH_SHORT).show()
            finishAffinity()
            exitProcess(0)
        }
    }




}