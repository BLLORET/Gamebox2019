package fr.epita.android.gamebox2019

import retrofit2.Call
import retrofit2.http.GET

interface IGame {
    @GET
    fun getAllGames() : Call<List<DGame>>
}