package fr.epita.android.gamebox2019

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_hangman_play.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.concurrent.schedule


class HangmanPlayFragment : Fragment() {

    companion object {
        fun newInstance(name: String): HangmanPlayFragment{
            val frag: HangmanPlayFragment = HangmanPlayFragment()
            var bundle: Bundle = Bundle()
            bundle.putString("playerName", name)

            frag.arguments = bundle

            return frag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hangman_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        /* FIXME: Get a real word */
        var word: CharArray = charArrayOf('T', 'A', 'B', 'O', 'U', 'R', 'E', 'T')
        var remainingTries: Int = 11
        remainingCount.text = getString(R.string.remainingTries) + " " + remainingTries.toString()

        var triedLettersList: ArrayList<Char> = ArrayList<Char>()
        var foundCount: Int = 0
        var displayedWord: CharArray = charArrayOf('_', '_', '_', '_', '_', '_', '_', '_')
        var letterViews = arrayOf(l0, l1, l2, l3, l4, l5, l6, l7)

        tryButton.setOnClickListener {
            if (tryLetter.text.isEmpty() || tryLetter.text.isBlank()) {
                tryLetter.text.clear()
                return@setOnClickListener
            }
            val letter: Char = tryLetter.text[0].toUpperCase()

            if (!letter.isLetter()) {
                Toast.makeText(activity, "Only letters are accepted ($letter)", Toast.LENGTH_SHORT).show()
            }
            else if (triedLettersList.contains(letter)) {
                Toast.makeText(activity, "You have already tried $letter", Toast.LENGTH_SHORT).show()
            }
            else {
                // Update the game
                triedLettersList.add(letter)
                triedLetters.text = "${triedLetters.text} $letter"

                var nbChanged: Int = 0
                for (n in 0 until 8) {
                    if (letter == word[n]) {
                        displayedWord[n] = letter
                        letterViews[n].text = letter.toString()
                        nbChanged += 1
                    }
                }

                if (nbChanged == 0) {
                    remainingTries -= 1
                    remainingCount.text = getString(R.string.remainingTries) + " " + remainingTries.toString()
                    if (remainingTries < 1) {
                        sendScore(false)
                    }
                }

                foundCount += nbChanged
                if (foundCount == 8) {
                    sendScore(true)
                }
            }
            tryLetter.text.clear()
        }

        /* FIXME: Remove this 60 seconds timer and put it in sliding puzzle */
        /*val timer = object: CountDownTimer(60000, 1024) {
            override fun onFinish() {
                sendScore(foundCount == 8)
            }

            override fun onTick(p0: Long) {
                remainingTime -= 1
                timerText.text = remainingTime.toString()
            }
        }
        timer.start()*/
    }

    fun sendScore(win: Boolean) {
        val status: String = if (win) "win" else "loose"
        val id: Int = 2

        // API request
        val baseURL = "https://androidlessonsapi.herokuapp.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(jsonConverter)
            .build()
        val service: IGame = retrofit.create(IGame::class.java)

        val callback = object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.code() == 200) {
                    Toast.makeText(activity, "Successfully recovered games detail", Toast.LENGTH_SHORT).show()

                    /* FIXME: redirect to score screen once it is done */
                    fragmentManager!!
                        .beginTransaction()
                        .replace(this@HangmanPlayFragment.id, GameDetailFragment.Factory.newInstance(2, true))
                        .commit()
                    /* FIXME END*/
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(activity, "Failed to recover games list", Toast.LENGTH_LONG).show()
                throw t
            }

        }

        val name: String = this.arguments!!.getString("playerName")!!

        service.sendScoreRequest(id, status, name).enqueue(callback)
    }
}
