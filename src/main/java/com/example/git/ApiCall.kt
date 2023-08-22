package com.example.git

import android.content.Context

import retrofit2.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCall {
    companion object {
    fun getData() :Call<List<gitUser>>{

        lateinit var users: List<gitUser>


        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()


        val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)


        val call: Call<List<gitUser>> = service.get("Bearer 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","{user}","1")



return call



    }


    }
}
