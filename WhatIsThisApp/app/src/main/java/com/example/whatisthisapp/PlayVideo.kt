package com.example.whatisthisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView

class PlayVideo : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.play_video)

        videoView = findViewById(R.id.videoView)
        btnBack = findViewById(R.id.btnBack)

        // Set up the MediaController
        val mediaController = MediaController(this)
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)

        val videoPath = "android.resource://" + packageName + "/" + R.raw.introduction_nz // Replace "introduction_nz" with the name of your video file in the "res/raw" folder
        val videoUri = Uri.parse(videoPath)
        videoView.setVideoURI(videoUri)
        videoView.requestFocus()
        videoView.start()


        btnBack.setOnClickListener{
            onBackPressed()
        }
    }
}