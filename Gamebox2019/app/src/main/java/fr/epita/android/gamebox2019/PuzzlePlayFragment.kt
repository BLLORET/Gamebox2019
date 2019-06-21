package fr.epita.android.gamebox2019


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_puzzle_play.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PuzzlePlayFragment : Fragment() {

    companion object {
        fun newInstance(name: String): PuzzlePlayFragment{
            val frag: PuzzlePlayFragment = PuzzlePlayFragment()
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
        return inflater.inflate(R.layout.fragment_puzzle_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var puzzleList : PuzzleList = PuzzleList(view)

        // Listeners for all images.
        imageView1.setOnClickListener {
            puzzleList.switchAndLoad(0, -1, 1, -1, 3)
            if (puzzleList.isWin()) {
                sendScore(true)
            }
        }
        imageView2.setOnClickListener {
            puzzleList.switchAndLoad(1, 0, 2, -1, 4)
            if (puzzleList.isWin()) {
                sendScore(true)
            }
        }
        imageView3.setOnClickListener {
            puzzleList.switchAndLoad(2, 1, -1, -1, 5)
            if (puzzleList.isWin()) {
                sendScore(true)
            }
        }
        imageView4.setOnClickListener {
            puzzleList.switchAndLoad(3, -1, 4, 0, 6)
            if (puzzleList.isWin()) {
                sendScore(true)
            }
        }
        imageView5.setOnClickListener {
            puzzleList.switchAndLoad(4, 3, 5, 1, 7)
            if (puzzleList.isWin()) {
                sendScore(true)
            }
        }
        imageView6.setOnClickListener {
            puzzleList.switchAndLoad(5, 4, -1, 2, 8)
            if (puzzleList.isWin()) {
                sendScore(true)
            }
        }
        imageView7.setOnClickListener {
            puzzleList.switchAndLoad(6, -1, 7, 3, -1)
            if (puzzleList.isWin()) {
                sendScore(true)
            }
        }
        imageView8.setOnClickListener {
            puzzleList.switchAndLoad(7, 6, 8, 4, -1)
            if (puzzleList.isWin()) {
                sendScore(true)
            }
        }
        imageView9.setOnClickListener {
            puzzleList.switchAndLoad(8, 7, -1, 5, -1)
            if (puzzleList.isWin()) {
                sendScore(true)
            }
        }

        puzzle_back_menu.setOnClickListener {
            (activity as MainActivity).printListGame()
        }
    }

    private fun sendScore(win: Boolean) {
        val status: String = if (win) "win" else "loose"
        val id: Int = 9

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
                    Toast.makeText(activity, "Score successfully sent", Toast.LENGTH_SHORT).show()

                    (activity as MainActivity).getScoreFragment("SlidingPuzzle")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(activity, "Failed to create score", Toast.LENGTH_LONG).show()
                throw t
            }
        }

        val name: String = this.arguments!!.getString("playerName")!!

        service.sendScoreRequest(id, status, name).enqueue(callback)
    }
}
