package com.example.myapplication.Network

import com.example.myapplication.gitUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ApiService {

    @GET("users")
    fun get(
      //  @Header("Authorization") accessToken: String,
        @Query("q") user: String,
        @Query("page") page: String
    ): Call<List<gitUser>>






}