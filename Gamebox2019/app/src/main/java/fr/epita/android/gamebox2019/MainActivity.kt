package fr.epita.android.gamebox2019

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private fun printCredits() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, CreditsFragment())
            .commit()
    }

    private fun printListGame() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_container, ListGameFragment())
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        printCredits()
        printListGame()
    }
}
