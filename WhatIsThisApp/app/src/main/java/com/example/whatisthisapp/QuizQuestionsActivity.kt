package com.example.whatisthisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class QuizQuestionsActivity : AppCompatActivity() {

    private val tvProgress: TextView = findViewById(R.id.tv_progress)
    private val tvQuestion: TextView = findViewById(R.id.tv_question)
    private val tvOptionOne: TextView = findViewById(R.id.tv_option_one)
    private val tvOptionTwo: TextView = findViewById(R.id.tv_option_two)
    private val tvOptionThree: TextView = findViewById(R.id.tv_option_three)
    private val tvOptionFour: TextView = findViewById(R.id.tv_option_three)
    private val ivImage: ImageView = findViewById(R.id.iv_image)
    private val progressBar: ProgressBar = findViewById(R.id.progress_Bar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_questions)

        val questionsList = Constants.getQuestions()
        Log.e("Questions Size", "${questionsList.size}")
        for (i in questionsList) {
            Log.e("Questions", i.question)
        }

        // TODO (Step 3: Setting the question in the UI from the list.)
        // START
        val currentPosition = 1 // Default and the first question position
        val question: Question? =
            questionsList[currentPosition - 1] // Getting the question from the list with the help of current position.

        progressBar.progress =
            currentPosition // Setting the current progress in the progressbar using the position of question
        tvProgress.text =
            "$currentPosition" + "/" + progressBar.getMax() // Setting up the progress text

        // Now set the current question and the options in the UI
        tvQuestion.text = question!!.question
        ivImage.setImageResource(question.image)
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
        tvOptionFour.text = question.optionFour
        // END
    }
}