package fr.epita.android.gamebox2019

import retrofit2.Call
import retrofit2.http.*

interface IGame {
    @GET
    fun getAllGames() : Call<List<DGame>>

    @GET("api/game/details")
    fun getGameDetail(@Query(value="game_id") game_id: Int): Call<DGameDetail>

    @POST("api/game/score")
    @FormUrlEncoded
    fun sendScoreRequest(@Field("game_id") game_id: Int, @Field("score") score: String, @Field("player") name: String): Call<String>
}