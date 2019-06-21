package fr.epita.android.gamebox2019

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private fun printCredits() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_container, CreditsFragment())
            .commit()
    }

    fun printListGame() {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, ListGameFragment())
            .commit()
        printCredits()
    }

    fun getDetailFragment(id: Int, playable: Boolean) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, GameDetailFragment.newInstance(id, playable))
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        printListGame()
    }
}
