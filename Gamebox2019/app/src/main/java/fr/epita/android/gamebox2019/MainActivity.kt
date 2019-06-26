package fr.epita.android.gamebox2019

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.epita.android.gamebox2019.game.PuzzlePlayFragment
import fr.epita.android.gamebox2019.menu.CreditsFragment
import fr.epita.android.gamebox2019.menu.GameDetailFragment
import fr.epita.android.gamebox2019.menu.ListGameFragment
import fr.epita.android.gamebox2019.menu.returnToMenuFragment
import fr.epita.android.gamebox2019.score.ScoreListFragment

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        printListGame()
    }
}
