package fr.epita.android.gamebox2019.score


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import fr.epita.android.gamebox2019.*
import fr.epita.android.gamebox2019.data.DScore
import fr.epita.android.gamebox2019.menu.IGame
import kotlinx.android.synthetic.main.fragment_score_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ScoreListFragment : Fragment() {

    companion object Factory{
        fun newInstance(game: String): ScoreListFragment {
            val frag: ScoreListFragment =
                ScoreListFragment()
            var bundle: Bundle = Bundle()
            bundle.putString("game", game)

            frag.arguments = bundle

            return frag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val baseURL = "https://androidlessonsapi.herokuapp.com/api/"

        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(jsonConverter)
            .build()

        val service: IGame = retrofit.create(IGame::class.java)

        var callback: Callback<MutableList<DScore>> = object : Callback<MutableList<DScore>> {

            override fun onFailure(call: Call<MutableList<DScore>>, t: Throwable) {
                scoreList.emptyView = emptyScore
                Log.w("Game", "Error by loading games because you are not connected to internet.")
            }

            override fun onResponse(call: Call<MutableList<DScore>>, response: Response<MutableList<DScore>>) {
                if (response.code() == 200) {
                    val data: MutableList<DScore>? = response.body()
                    if (data != null) {
                        val gameName: String = arguments!!.getString("game")!!
                        if (gameName.equals("")) {
                            scoreList.adapter = ScoreListAdapter(
                                activity as MainActivity,
                                data
                            )
                        }
                        else {
                            val hangmanScores: MutableList<DScore> = arrayListOf()
                            for (elt in data) {
                                if (elt.game == gameName) {
                                    hangmanScores.add(elt)
                                }
                            }
                            scoreList.adapter = ScoreListAdapter(
                                activity as MainActivity,
                                hangmanScores
                            )
                        }
                    }
                } else {
                    scoreList.emptyView = emptyScore
                }
            }
        }
        service.getAllScores().enqueue(callback)
    }
}
