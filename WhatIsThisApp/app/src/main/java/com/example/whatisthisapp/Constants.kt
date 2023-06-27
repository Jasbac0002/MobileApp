package com.example.whatisthisapp

    class Constants {
        companion object {
        fun getQuestions(): ArrayList<Question> {
            val questionList = ArrayList<Question>()

            val q1 = Question(
                id = 1,
                question = "What is this Fruit?",
                image = R.drawable.fruits_kiwi,
                optionOne = "Guava",
                optionTwo = "Kiwi Fruit",
                optionThree = "Mango",
                optionFour = "Apple",
                correctAnswer = 2
            )

            // add to questionList Array
            questionList.add(q1)

            val q2 = Question(
                id = 2,
                question = "What is this Fruit?",
                image = R.drawable.fruits_boysenberry,
                optionOne = "Grapes",
                optionTwo = "Dates",
                optionThree = "Boysenberry",
                optionFour = "BlackCurrant",
                correctAnswer = 3
            )

            // add to questionList Array
            questionList.add(q2)

            val q3 = Question(
                id = 3,
                question = "What is this Fruit?",
                image = R.drawable.fruits_feijoa,
                optionOne = "Feijoa",
                optionTwo = "Quince",
                optionThree = "Persimmon",
                optionFour = "Kiwano",
                correctAnswer = 1
            )

            // add to questionList Array
            questionList.add(q3)

            val q4 = Question(
                id = 4,
                question = "What is this Fruit?",
                image = R.drawable.fruits_tamarillo,
                optionOne = "Plum",
                optionTwo = "RedBellPepper",
                optionThree = "PassionFruit",
                optionFour = "Tamarillo",
                correctAnswer = 4
            )

            // add to questionList Array
            questionList.add(q4)

            return questionList
        }
    }
}
