package com.example.whatisthisapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AboutApp : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.about_app)

        val btnBack: Button = findViewById<Button>(R.id.btn_appinfo_back)

        btnBack.setOnClickListener{
            onBackPressed()
        }

        val webView = findViewById<WebView>(R.id.wv_website)

    // Enable JavaScript (if needed)
            webView.settings.javaScriptEnabled = true

    // Load the local HTML file
            webView.loadUrl("file:///android_asset/whatisthis.html")

    }
}