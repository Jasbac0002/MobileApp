package com.example.whatisthisapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class QuizActivity : AppCompatActivity() {

    lateinit var questionsList: ArrayList<QuestionModel>
    private var index: Int = 0
    lateinit var questionModel: QuestionModel

    private var correctAnswerCount: Int = 0
    private var wrongAnswerCount: Int = 0

    lateinit var countDown: TextView
    lateinit var questionImage: ImageView


    lateinit var option1: Button
    lateinit var option2: Button
    lateinit var option3: Button
    lateinit var option4: Button


    private var backPressedTime: Long = 0
    private var backToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.quiz_activity)

        supportActionBar?.hide()

        countDown = findViewById(R.id.countdown)
        questionImage = findViewById(R.id.questionImage)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)


        //declare to the the value of radiobutton
        val selectedOption = intent.getStringExtra("selectedOption")

        questionsList = ArrayList()

        if (selectedOption == "Fruits") {
            // Add fruit questions to the questionsList

            questionsList = ArrayList()
            questionsList.add(
                QuestionModel(
                    R.drawable.fruits_kiwi,
                    "KiwiFruit",
                    "Atis",
                    "Indian Mango",
                    "Babana",
                    "KiwiFruit"
                )
            )
            questionsList.add(
                QuestionModel(
                    R.drawable.fruits_feijoa,
                    "Peras",
                    "Santol",
                    "Feijoa",
                    "Bayabas",
                    "Feijoa"
                )
            )
            questionsList.add(
                QuestionModel(
                    R.drawable.fruits_boysenberry,
                    "Ubas",
                    "Seresa",
                    "Duhat",
                    "Boysenberry",
                    "Boysenberry"
                )
            )
            questionsList.add(
                QuestionModel(
                    R.drawable.fruits_tamarillo,
                    "Kamatis",
                    "Siling Pula",
                    "Manggustin",
                    "Tamarillo",
                    "Tamarillo"
                )
            )
            questionsList.add(
                QuestionModel(
                    R.drawable.fruit_persimmon,
                    "Suha",
                    "KiyatKiyat",
                    "Persimmon",
                    "GrapeFruit",
                    "Persimmon"
                )
            )
            // Add more fruit questions as needed
        } else if (selectedOption == "Animals") {
            // Add animal questions to the questionsList

            questionsList = ArrayList()
            questionsList.add(
                QuestionModel(
                    R.drawable.animal_hectors_dolphin,
                    "Hector's Dolphin",
                    "Killer Whale",
                    "Pating",
                    "Balyena",
                    "Hector's Dolphin"
                )
            )
            questionsList.add(
                QuestionModel(
                    R.drawable.animal_tuatara,
                    "Khalessi Dragon",
                    "Trex",
                    "Tuatara",
                    "Buaya",
                    "Tuatara"
                )
            )
            questionsList.add(
                QuestionModel(
                    R.drawable.animal_kea_parrot,
                    "Agila",
                    "Ibong Adarna",
                    "Paniki",
                    "Kea Parrot",
                    "Kea Parrot"
                )
            )
            questionsList.add(
                QuestionModel(
                    R.drawable.animal_kiwi_bird,
                    "Kiwi Bird",
                    "Pugo",
                    "Manok",
                    "Dove",
                    "Kiwi Bird"
                )
            )
            questionsList.add(
                QuestionModel(
                    R.drawable.animals_nz_falcon,
                    "Eagle",
                    "Mucho",
                    "San Mig",
                    "NZ Falcon",
                    "NZ Falcon"
                )
            )
            // Add more animal questions as needed
        }



        //questionsList.shuffle()
        questionModel = questionsList[index]

        setAllQuestions()
        countdown()

    }

    fun countdown() {
        //Set Timer , 10 for 10seconds
        var duration: Long = TimeUnit.SECONDS.toMillis(3)


        object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                var sDuration: String = String.format(
                    Locale.ENGLISH,
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                    )
                )

                countDown.text = sDuration

            }

            override fun onFinish() {
                index++
                if (index < questionsList.size) {
                    questionModel = questionsList[index]
                    setAllQuestions()
                    resetBackground()
                    enableButton()
                    countdown()

                } else {

                    gameResult()

                }


            }


        }.start()


    }


    private fun correctAns(option: Button) {
        option.background = getDrawable(R.drawable.right_bg)

        correctAnswerCount++


    }

    private fun wrongAns(option: Button) {

        option.background = resources.getDrawable(R.drawable.wrong_bg)

        wrongAnswerCount++


    }

    private fun setAllQuestions() {
        questionImage.setImageResource(questionModel.questionImage)
        option1.text = questionModel.option1
        option2.text = questionModel.option2
        option3.text = questionModel.option3
        option4.text = questionModel.option4
    }

    private fun enableButton() {
        option1.isClickable = true
        option2.isClickable = true
        option3.isClickable = true
        option4.isClickable = true
    }

    private fun disableButton() {
        option1.isClickable = false
        option2.isClickable = false
        option3.isClickable = false
        option4.isClickable = false
    }

    private fun resetBackground() {
        option1.background = resources.getDrawable(R.drawable.option_bg)
        option2.background = resources.getDrawable(R.drawable.option_bg)
        option3.background = resources.getDrawable(R.drawable.option_bg)
        option4.background = resources.getDrawable(R.drawable.option_bg)
    }

    private fun gameResult() {
        var intent = Intent(this, ResultsActivity::class.java)

        intent.putExtra("correct", correctAnswerCount.toString())
        intent.putExtra("total", questionsList.size.toString())

        startActivity(intent)
    }


    fun option1Clicked(view: View) {
        disableButton()
        if (questionModel.option1 == questionModel.answer) {
            option1.background = resources.getDrawable(R.drawable.right_bg)

            correctAns(option1)

        } else {
            wrongAns(option1)
        }
    }

    fun option2Clicked(view: View) {
        disableButton()
        if (questionModel.option2 == questionModel.answer) {
            option2.background = resources.getDrawable(R.drawable.right_bg)


            correctAns(option2)

        } else {
            wrongAns(option2)
        }
    }

    fun option3Clicked(view: View) {
        disableButton()
        if (questionModel.option3 == questionModel.answer) {

            option3.background = resources.getDrawable(R.drawable.right_bg)


            correctAns(option3)


        } else {
            wrongAns(option3)
        }
    }

    fun option4Clicked(view: View) {
        disableButton()
        if (questionModel.option4 == questionModel.answer) {
            option4.background = resources.getDrawable(R.drawable.right_bg)


            correctAns(option4)

        } else {
            wrongAns(option4)
        }
    }


}