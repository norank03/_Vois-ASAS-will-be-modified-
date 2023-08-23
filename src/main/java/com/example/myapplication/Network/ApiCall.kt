package com.example.myapplication.Network

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myapplication.gitUser

import retrofit2.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiCall {

    companion object {

        var data: List<gitUser> by mutableStateOf(listOf())


        fun getsdata(): Call<List<gitUser>> {




            val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()


            val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)


            val call: Call<List<gitUser>> =
                service.get( "{user}", "1")




            return call
        }

    }
}