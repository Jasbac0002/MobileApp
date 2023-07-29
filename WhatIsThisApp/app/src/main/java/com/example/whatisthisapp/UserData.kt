package com.example.whatisthisapp

//This will hold the global name for the game
class UserData private constructor() {

    var playerName: String = ""

    companion object {
        private val instance = UserData()

        fun getInstance(): UserData {
            return instance
        }
    }
}