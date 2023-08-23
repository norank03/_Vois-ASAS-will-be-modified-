package com.example.myapplication

import android.annotation.SuppressLint

import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.Pink40

import androidx.compose.runtime.Composable
import com.example.myapplication.Network.ApiCall


import java.util.*

import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.md_theme_dark_inversePrimary
import com.example.myapplication.ui.theme.md_theme_dark_secondary
import com.example.myapplication.ui.theme.md_theme_light_onErrorContainer
import com.example.myapplication.ui.theme.md_theme_light_onPrimaryContainer
import com.example.myapplication.ui.theme.md_theme_light_tertiary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {

    lateinit var dataincompose: List<gitUser>
    var data: List<gitUser> by mutableStateOf(listOf())
    val tag: String = "jj"

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {


                Scaffold(topBar = { TopBar() }, backgroundColor = md_theme_dark_inversePrimary ) {
                    githubNav()


                }
            }
        }



        val myCall: Call<List<gitUser>> = ApiCall.getsdata()

        myCall.enqueue(object : Callback<List<gitUser>> {


            override fun onResponse(
                myCall: Call<List<gitUser>>,
                response: Response<List<gitUser>>
            ) {

                dataincompose = response.body()!!

                Log.d(tag, dataincompose.toString())
                data = dataincompose
                dataa(data)

            }

            override fun onFailure(call: Call<List<gitUser>>, t: Throwable) {

            }
        })
    }


}
