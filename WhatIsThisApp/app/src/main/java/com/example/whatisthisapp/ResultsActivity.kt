package com.example.whatisthisapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlin.system.exitProcess

class ResultsActivity : AppCompatActivity() {

    private lateinit var correctAns: TextView
    private lateinit var totalAns: TextView
    private lateinit var performance: TextView
    private lateinit var output: LinearLayout
    private lateinit var listViewTopPlayers: ListView
    private val playerRecordsList: MutableList<PlayerRecord> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results_activity)

        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        supportActionBar?.hide()

        setupViews()
        retrieveAndDisplayResults()
        updateNewScoreInFirestore()

        val handler = Handler()
        handler.postDelayed({}, 2000) // Delay time in milliseconds (1.5 seconds)

        loadTopPlayersAndSetupButtons()
    }

    private fun setupViews() {
        correctAns = findViewById(R.id.correctAns)
        totalAns = findViewById(R.id.totalAns)
        performance = findViewById(R.id.performance)
        output = findViewById(R.id.output)
    }

    private fun retrieveAndDisplayResults() {
        val intent = intent
        val correctAnsNo = intent.getStringExtra("correct")
        val totalAnsNo = intent.getStringExtra("total")
        correctAns.text = correctAnsNo
        totalAns.text = totalAnsNo

        val percentage = (correctAnsNo?.toFloat()?.div(totalAnsNo?.toFloat()!!))?.times(100)

        when {
            50 <= percentage!! && percentage <= 99 -> {
                performance.text = "You're Getting There!"
                output.background = resources.getDrawable(R.drawable.option_bg)
            }

            percentage >= 100 -> {
                performance.text = "Perfect Your Awesome!"
                output.background = resources.getDrawable(R.drawable.right_bg)
            }

            percentage < 50 -> {
                performance.text = "More Practice :)"
                output.background = resources.getDrawable(R.drawable.wrong_bg)
            }
        }
    }

    private fun updateNewScoreInFirestore() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid
        val db = FirebaseFirestore.getInstance()

        // Replace "users" with your Firestore collection name where user data is stored
        if (userId != null) {
            val newScoreText = correctAns.text.toString()
            val newScore = newScoreText.toIntOrNull()
            val userRef = db.collection("players").document(userId)

            userRef.update("quiz_score", newScore)
                .addOnSuccessListener {
                    // Score updated successfully
                    Toast.makeText(this, "Score updated successfully!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    // Failed to update score
                    Toast.makeText(this, "Failed to update score. Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }


    }

    private fun loadTopPlayersAndSetupButtons() {
        listViewTopPlayers = findViewById(R.id.listViewTopPlayers)

        // Retrieve data from Firestore
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("players")
            .orderBy("quiz_score", Query.Direction.DESCENDING)
            .limit(5)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val playerName = document.getString("name") ?: ""
                    val quizScore = document.getLong("quiz_score")?.toInt() ?: 0
                    val playerRecord = PlayerRecord(playerName, quizScore)
                    playerRecordsList.add(playerRecord)
                }

                // Create and set the adapter for the ListView
                val adapter = PlayerRecordsAdapter(this, playerRecordsList)
                listViewTopPlayers.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error getting documents: ", exception)
            }

        val btnPlayAgain: Button = findViewById<Button>(R.id.btnPlayAgain)
        val btnExit: Button = findViewById<Button>(R.id.btnExitGame)

        btnPlayAgain.setOnClickListener {
            val intent = Intent(this, QuizSettings::class.java)
            startActivity(intent)
        }

        btnExit.setOnClickListener {
            val RateMe: Any = 5

            // Use safe casting with 'as?' to handle possible type conversion errors
            val IntRateMe: Int? = RateMe as? Int

            if (IntRateMe != null) {
                Toast.makeText(this, "Thank You! For Playing my App!\nPlease Rate me $IntRateMe -JB", Toast.LENGTH_SHORT).show()
                finishAffinity()
                exitProcess(0)
            } else {
                // Handle the case when typecasting fails
                // For example, if 'RateMe' is not an Int, the 'IntRateMe' will be null
                Toast.makeText(this, "Error: Could not convert to Int.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val TAG = "TopPlayersActivity"
    }
}
