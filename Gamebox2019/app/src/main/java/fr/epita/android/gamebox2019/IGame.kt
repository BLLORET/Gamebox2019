package fr.epita.android.gamebox2019

import retrofit2.Call
import retrofit2.http.*

interface IGame {

    @GET("game/list")
    fun getAllGames() : Call<MutableList<DGame>>

    @GET("api/game/details")
    fun getGameDetail(@Query(value="game_id") game_id: Int): Call<DGameDetail>

    @POST("api/game/score")
    @FormUrlEncoded
    fun sendScoreRequest(@Field("game_id") game_id: Int, @Field("score") score: String, @Field("player") name: String): Call<String>
}