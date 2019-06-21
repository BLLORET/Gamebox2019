package fr.epita.android.gamebox2019

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    public fun printCredits() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, CreditsFragment())
            .commit()
    }

    public fun printListGame() {
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
        /*supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, ScoreListFragment())
            .add(R.id.main_container, returnToMenuFragment())
            .commit()*/


        /* TODO: Remove this */
        /*supportFragmentManager
            .beginTransaction()
            .replace(R.id.main, GameDetailFragment.newInstance(2, true))
            .commit()*/
    }
}
