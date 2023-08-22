package com.example.git

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {

    @GET("users")
    fun get(
        @Header("Authorization") accessToken: String,
        @Query("q") user: String,
        @Query("page") page: String
    ): Call<List<gitUser>>


    // suspend fun getUserNames() : List<gitUser>
//interciptor

    @GET("search/users")
    fun get(
        @Header("Authorization") accessToken: String,
        @Query("q") user: String
    )

    //search


}