package fr.epita.android.gamebox2019

import android.util.Log

class DGame (
    val id : Int,
    val name : String,
    val picture : String
) {
    val playable = (name == "Hangman" || name == "SlidingPuzzle")
}