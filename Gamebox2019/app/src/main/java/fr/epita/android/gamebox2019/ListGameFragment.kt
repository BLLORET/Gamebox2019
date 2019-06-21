package fr.epita.android.gamebox2019


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list_game.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListGameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val baseURL = "https://androidlessonsapi.herokuapp.com/api/"

        // Use GSON library to create our JSON parser
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        // Create a Retrofit client object targeting the provided URL
        // and add a JSON converter (because we are expecting json responses)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(jsonConverter)
            .build()

        // Use the client to create a service:
        // an object implementing the interface to the WebService
        val service: IGame = retrofit.create(IGame::class.java)

        var gamesCallback: Callback<MutableList<DGame>> = object : Callback<MutableList<DGame>> {

            override fun onFailure(call: Call<MutableList<DGame>>, t: Throwable) {
                list_games.emptyView = empty_list_view
                Log.w("Game", "Error by loading games because you are not connected to internet.")
            }

            override fun onResponse(call: Call<MutableList<DGame>>, response: Response<MutableList<DGame>>) {

                if (response.code() == 200) {
                    var data: MutableList<DGame>? = response.body()
                    if (data != null) {
                        data = data.shuffled() as MutableList<DGame>
                        list_games.adapter = GameListAdapter(activity as MainActivity, data)
                        list_games.setOnItemClickListener {
                                adapterView, _, position, _ ->
                            var game : DGame = adapterView.getItemAtPosition(position) as DGame
                            val playable : Boolean = (game.name == "Hangman" || game.name == "SlidingPuzzle")
                            // To Remove in production, the true is to test the play game until data class bug is fixed.
                            (activity as MainActivity).getDetailFragment(game.id/*, game.playable*/, playable)
                        }
                    }
                } else {
                    list_games.emptyView = empty_list_view
                }
            }
        }
        service.getAllGames().enqueue(gamesCallback)
    }
}
