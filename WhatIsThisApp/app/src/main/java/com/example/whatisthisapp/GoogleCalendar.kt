package com.example.whatisthisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class GoogleCalendar : AppCompatActivity() {

    private  lateinit var etTitle : EditText
    private  lateinit var etLocation : EditText
    private  lateinit var etDescription : EditText
    private  lateinit var btnAdd : Button
    private  lateinit var btnBack : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.google_calendar)


        etTitle = findViewById(R.id.etTitle)
        etDescription = findViewById(R.id.etDescription)
        etLocation = findViewById(R.id.etLocation)
        btnAdd = findViewById(R.id.btnAdd)
        btnBack = findViewById(R.id.btn_addQuizBack)


        btnAdd.setOnClickListener(View.OnClickListener {
            if (!etTitle.text.toString().isEmpty() && !etLocation.text.toString().isEmpty() && !etDescription.text.toString().isEmpty()){
                val calendarIntent = Intent(Intent.ACTION_INSERT).apply {
                    data = CalendarContract.Events.CONTENT_URI
                    putExtra(CalendarContract.Events.TITLE, etTitle.text.toString())
                    putExtra(CalendarContract.Events.EVENT_LOCATION, etLocation.text.toString())
                    putExtra(CalendarContract.Events.DESCRIPTION, etDescription.text.toString())
                    putExtra(CalendarContract.Events.ALL_DAY, true)
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("mew@gmail.com", "mark@gmail.com", "jasper@gmail.com"))
                }

                if (calendarIntent.resolveActivity(packageManager) != null) {
                    // Google Calendar or a calendar app is installed
                    startActivity(calendarIntent)
                } else {
                    // No app installed that can handle the calendar intent
                    Toast.makeText(this@GoogleCalendar, "There is no app that can support this action", Toast.LENGTH_SHORT).show()
                }
            }


        })

        btnBack.setOnClickListener{
            onBackPressed()
        }

    }
}