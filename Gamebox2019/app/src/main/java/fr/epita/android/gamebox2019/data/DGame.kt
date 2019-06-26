package fr.epita.android.gamebox2019.data

class DGame (val id : Int, val name : String, val picture : String) {
    fun isPlayable() : Boolean {
        return name == "Hangman" || name == "SlidingPuzzle"
    }
}