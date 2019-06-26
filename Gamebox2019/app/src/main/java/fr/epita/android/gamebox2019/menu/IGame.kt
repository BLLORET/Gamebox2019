package fr.epita.android.gamebox2019.menu

import fr.epita.android.gamebox2019.data.DGame
import fr.epita.android.gamebox2019.data.DGameDetail
import fr.epita.android.gamebox2019.data.DScore
import retrofit2.Call
import retrofit2.http.*

interface IGame {

    @GET("game/list")
    fun getAllGames() : Call<MutableList<DGame>>

    @GET("game/scores")
    fun getAllScores(): Call<MutableList<DScore>>

    @GET("api/game/details")
    fun getGameDetail(@Query(value="game_id") game_id: Int): Call<DGameDetail>

    @POST("api/game/score")
    @FormUrlEncoded
    fun sendScoreRequest(@Field("game_id") game_id: Int, @Field("score") score: String,
                         @Field("player") name: String): Call<String>
}