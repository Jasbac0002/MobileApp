package com.example.whatisthisapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class QuizSettings : AppCompatActivity() {

    private lateinit var tvPlayerName: TextView
    private lateinit var btnBack: Button
    private lateinit var btnBeginQuiz: Button
    private lateinit var btnAddQuizEvent: Button
    private lateinit var rssImage: ImageView
    private lateinit var fbImage: ImageView
    private lateinit var instaImage: ImageView
    private lateinit var twitterImage: ImageView
    private lateinit var playVideoImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        setContentView(R.layout.quiz_settings)

        tvPlayerName = findViewById(R.id.tvPlayerName)
        btnBack = findViewById(R.id.btn_quizsettings_back)
        btnBeginQuiz = findViewById(R.id.btn_BeginQuiz)
        btnAddQuizEvent = findViewById(R.id.btn_addQuizEventCalendar)
        rssImage = findViewById(R.id.rssImage)
        fbImage = findViewById(R.id.fbImage)
        instaImage = findViewById(R.id.instaImage)
        twitterImage = findViewById(R.id.twitterImage)
        playVideoImage = findViewById(R.id.playVideoImage)

        //Display the global name of the player
        tvPlayerName.text = "Hello, " + UserData.getInstance().playerName + "!"


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

        btnAddQuizEvent.setOnClickListener{
            val intent = Intent(this, GoogleCalendar::class.java)
            startActivity(intent)
        }

        playVideoImage.setOnClickListener{
            val intent = Intent(this, PlayVideo::class.java)
            startActivity(intent)
        }

        rssImage.setOnClickListener{
            val intent = Intent(this, RssFeeds::class.java)
            startActivity(intent)
        }

        fbImage.setOnClickListener{
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_TEXT, "Sharing to FB : WhatIsthisApp Quizz Game is Awesome!")
            share.setPackage("com.facebook.lite") //Facebook App package
            startActivity(Intent.createChooser(share, "Share to FB!"))
        }
        twitterImage.setOnClickListener{
            val tweetUrl = ("https://twitter.com/intent/tweet?text=Twitting This! : WhatIsthisApp Quizz Game is Awesome!")
            val uri = Uri.parse(tweetUrl)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        instaImage.setOnClickListener{
            // Replace "your_image_filename" with the actual name of your image file without the extension.
            val imageUri = Uri.parse("android.resource://" + packageName + "/drawable/splash_logo")
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "image/*"
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
            shareIntent.setPackage("com.instagram.android") // Instagram App package

            try {
                startActivity(shareIntent)
            } catch (e: ActivityNotFoundException) {
                // Instagram app is not installed, handle this case accordingly
            }

        }

        btnBack.setOnClickListener{
            onBackPressed()
        }
    }
}