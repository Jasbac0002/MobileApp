package com.example.whatisthisapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import android.widget.ListView


class TopPlayersActivity : AppCompatActivity() {

    private lateinit var listViewTopPlayers: ListView
    private val playerRecordsList: MutableList<PlayerRecord> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.top_players)

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
    }

    companion object {
        private const val TAG = "TopPlayersActivity"
    }
}