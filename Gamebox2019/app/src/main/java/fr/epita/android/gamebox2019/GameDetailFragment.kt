package fr.epita.android.gamebox2019


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_game_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GameDetailFragment : Fragment() {

    companion object Factory {
        fun newInstance(id: Int, playable: Boolean): GameDetailFragment {
            val frag: GameDetailFragment = GameDetailFragment()
            val bundle: Bundle = Bundle()
            bundle.putInt("ID", id)
            bundle.putBoolean("PLAYABLE", playable)
            frag.arguments = bundle
            return frag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Disable play and name fields if the game is not playable
        if (!arguments!!.getBoolean("PLAYABLE")){
            editName.isEnabled = false
            play.isEnabled = false
        }

        // API request
        var data: DGameDetail
        var buttonUrl: String = ""
        val baseURL = "https://androidlessonsapi.herokuapp.com/"
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(jsonConverter)
            .build()
        val service: IGame = retrofit.create(IGame::class.java)

        val callback = object : Callback<DGameDetail> {
            override fun onResponse(call: Call<DGameDetail>, response: Response<DGameDetail>) {
                if (response.code() == 200) {
                    data = response.body()!!
                    Toast.makeText(activity, "Successfully recovered games detail", Toast.LENGTH_SHORT).show()
                    val obj: DGameDetail = data
                    nameObj.text = obj.name
                    typeObj.text = obj.type
                    nbplayerObj.text = obj.players.toString()
                    yearObj.text = obj.year.toString()
                    desc.text = obj.description_en
                    Glide.with(this@GameDetailFragment).load(obj.picture).into(image)
                    buttonUrl = obj.url
                }
            }

            override fun onFailure(call: Call<DGameDetail>, t: Throwable) {
                Toast.makeText(activity, "Failed to recover games list", Toast.LENGTH_LONG).show()
                throw t
            }

        }

        service.getGameDetail(arguments!!.getInt("ID")).enqueue(callback)

        more.setOnClickListener {
            val webpage: Uri = Uri.parse(buttonUrl)
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(intent)
        }

        play.setOnClickListener {
            val name: String = editName.text.toString()
            if (!name.isEmpty()) {
                fragmentManager!!
                    .beginTransaction()
                    .replace(this@GameDetailFragment.id, HangmanPlayFragment.newInstance(name))
                    .commit()
            }
        }
    }
}
