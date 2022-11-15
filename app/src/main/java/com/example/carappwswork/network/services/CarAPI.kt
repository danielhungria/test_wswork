package com.example.carappwswork.network.services

import com.example.carappwswork.model.CarListItem
import com.example.carappwswork.model.CarUsuario
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CarAPI {
    @GET("cars.json")
    fun getData(): Call<List<CarListItem>>

    @POST("cars/leads/")
    suspend fun salva(@Body carUsuario: List<CarUsuario>):Response<String>
}
