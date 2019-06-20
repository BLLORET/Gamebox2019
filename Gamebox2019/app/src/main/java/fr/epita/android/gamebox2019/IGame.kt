package fr.epita.android.gamebox2019

import retrofit2.Call
import retrofit2.http.GET

interface IGame {
    @GET("game/list")
    fun getAllGames() : Call<MutableList<DGame>>
}