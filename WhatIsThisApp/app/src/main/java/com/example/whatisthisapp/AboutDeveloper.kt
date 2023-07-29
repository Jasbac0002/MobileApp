package com.example.whatisthisapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutDeveloper : AppCompatActivity() {

    private lateinit var dataManager: DataManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.about_developer)


        val btnBack: Button = findViewById<Button>(R.id.btn_appinfo_back)
        val btnCallMe: Button = findViewById<Button>(R.id.btn_callme)
        val btnEmailMe: Button = findViewById<Button>(R.id.btn_email_me)
        val btnLocateMe: Button = findViewById<Button>(R.id.btn_locateme)
        val btnBrowse: Button = findViewById<Button>(R.id.btn_browse)
        val tvDevelopers: TextView = findViewById<Button>(R.id.tvDevelopers)



        dataManager = DataManager(this)
        // Query all names and display them in the TextView
        val allNames = dataManager.getAllNames()
        tvDevelopers.text = "Developed by: \n\n" + allNames.joinToString("\n")

        btnBack.setOnClickListener{
            onBackPressed()
        }

        btnCallMe.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel: " + "+6422333444")
            startActivity(intent)
        })

        btnEmailMe.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("jaba008@ess.ais.ac.nz"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Emailing You About the App")
            intent.putExtra(Intent.EXTRA_TEXT, "Hello! Jasper, Your App is nice, Email Function is Working!")
            intent.type = "message/rfc822"
            startActivity(Intent.createChooser(intent, "Choose an Email client:"))
        })

        btnLocateMe.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(this@AboutDeveloper, GoogleMaps::class.java)
            startActivity(intent)
        })

        btnBrowse.setOnClickListener(View.OnClickListener { view: View? ->
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ais.ac.nz/"))
            startActivity(browserIntent)
        })

    }


}