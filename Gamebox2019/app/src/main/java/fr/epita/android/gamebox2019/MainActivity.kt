package fr.epita.android.gamebox2019

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    fun printListGame() {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, ListGameFragment())
            .add(R.id.main_container, CreditsFragment())
            .commit()
    }

    fun getDetailFragment(id: Int, playable: Boolean) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, GameDetailFragment.newInstance(id, playable))
            .commit()
    }

    fun getScoreFragment(game: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, ScoreListFragment.newInstance(game))
            .add(R.id.main_container, returnToMenuFragment())
            .commit()
    }

    fun playPuzzle() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, PuzzlePlayFragment())
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //printListGame()
        playPuzzle()
    }
}
