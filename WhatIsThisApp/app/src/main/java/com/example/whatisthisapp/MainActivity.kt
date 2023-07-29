package com.example.whatisthisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        //Declaring variables
        auth = FirebaseAuth.getInstance()
        val signInButton: Button = findViewById<Button>(R.id.signInButton)
        val registerButton: Button = findViewById<Button>(R.id.registerButton)
        val nameEditText: EditText = findViewById<EditText>(R.id.nameEditText)
        val passwordEditText: EditText = findViewById<EditText>(R.id.passwordEditText)
        val testingMode = 1 //if 1 set name and password for testing purposes

        if (testingMode == 1) {
            // Set initial values for EditText in Testing Mode
            nameEditText.setText("Jas")
            passwordEditText.setText("Testing")
        }

        //auth.signOut()

        val rootView = findViewById<View>(android.R.id.content)
        rootView.setOnClickListener {
            hideKeyboard()
        }


        // Sign in button click listener
        signInButton.setOnClickListener {
            val username = nameEditText.text.toString()
            val password = passwordEditText.text.toString()
            signIn(username, password)
        }

        // Register button click listener
        registerButton.setOnClickListener {
            val username = nameEditText.text.toString()
            val password = passwordEditText.text.toString()
            register(username, password)
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

    private fun signIn(username: String, password: String) {
        val email = "$username@gmail.com" // Append "@gmail.com" to the username

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign-in successful, proceed to dashboard
                    // Show a success Toast message
                    Toast.makeText(this, "Sign-in successful!", Toast.LENGTH_SHORT).show()

                    //set the Global Playername to the Name Signed in
                    UserData.getInstance().playerName = "$username"

                    val intent = Intent(this, HomeScreen::class.java)
                    startActivity(intent)

                } else {
                    // Sign-in failed, handle the error (e.g., show a Toast)
                    Toast.makeText(this, "Sign-in failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun register(username: String, password: String) {
        val email = "$username@gmail.com" // Append "@gmail.com" to the username

        if (password.length < 6) {
            // Show a Toast message if the password is below 6 characters
            Toast.makeText(this, "Password must be at least 6 characters.", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration successful, get the registered user's UID
                    val user = auth.currentUser
                    val userId = user?.uid

                    // Now, add the initial score document for the user in Firestore
                    if (userId != null) {
                        val db = FirebaseFirestore.getInstance()
                        val initialScore = 0
                        //val playerName = "$username"

                        val userRef = db.collection("players").document(userId)

                        //userRef.set(hashMapOf("quiz_score" to initialScore))

                        // Create a user data object with name, email, and initial quiz score
                        val userData = hashMapOf(
                            "name" to username,
                            "quiz_score" to initialScore
                        )

                        userRef.set(userData)
                            .addOnSuccessListener {
                                // Initial score document added successfully
                                // Proceed to the next screen or perform any required actions
                                // Show a success Toast message
                                Toast.makeText(
                                    this,
                                    "Registration successful and User added to Remote Database!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener { e ->
                                // Failed to add the initial score document
                                Toast.makeText(
                                    this,
                                    "Registration successful But FAILED to be added to Remote Database!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }

                } else {
                    // Registration failed, handle the error (e.g., show a Toast)
                    Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
    }


}