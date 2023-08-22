package com.example.git
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
import com.example.git.ui.theme.Pink80
import  com.example.git.ApiCall
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

import androidx.lifecycle.liveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.*


class MainActivity : ComponentActivity() {

    lateinit var dataincompose: List<gitUser>
    var data: List<gitUser> by mutableStateOf(listOf())
    val tag: String = "jj"

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {
                Scaffold(topBar = { TopBar() }, backgroundColor = Pink80) {
                    githubNav()
                    //MovieList(movieList = data)
                }


            }


        }

    }


    override fun onResume() {
        super.onResume()
        val myCall: Call<List<gitUser>> = ApiCall.getData()

        myCall.enqueue(object : Callback<List<gitUser>> {


            override fun onResponse(
                myCall: Call<List<gitUser>>,
                response: Response<List<gitUser>>
            ) {

                dataincompose = response.body()!!

                Log.d(tag, dataincompose.toString())
                data = dataincompose

            }

            override fun onFailure(call: Call<List<gitUser>>, t: Throwable) {

            }
        })


    }

    @Composable
    fun TopBar() {

        TopAppBar(
            title =
            {
                Text(
                    text = " User Name",
                    Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            backgroundColor = Color.Blue,
            contentColor = Color.White
        )


    }


    @Composable
    fun githubNav() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "User Name") {
            composable("User Name") {
                NamesListScreen(navController)
            }
        }
    }


    @Composable
    fun NamesListScreen(navController: NavHostController) {
        val textVal = remember {
            mutableStateOf(TextFieldValue(""))

        }

        Column {
            searchNamesList(textVal)
            MovieList(textVal, movieList = data)

        }
    }


    @Composable
    fun searchNamesList(textVal: MutableState<TextFieldValue>) {
        TextField(
            value = textVal.value,
            onValueChange = { textVal.value = it },
            placeholder = { Text(text = "Search  Name", color = Color.Black) },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(Color.Black, fontSize = 18.sp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            },
            trailingIcon = {
                if (textVal.value != TextFieldValue("")) {
                    IconButton(onClick = {
                        textVal.value = TextFieldValue("")
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close, contentDescription = "Close",
                            Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RectangleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                cursorColor = Color.Black,
                leadingIconColor = Color.Black,
                trailingIconColor = Color.Black,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent

            )
        )
    }


    @Composable
    fun MovieList(textVal: MutableState<TextFieldValue>, movieList: List<gitUser>) {

        val searchText = textVal.value.text




        LazyColumn {
            if (searchText.isEmpty()) {
                itemsIndexed(items = movieList) { index, item ->
                    NamItem(movie = item)
                }
            } else {

              val listis: List<gitUser> = movieList.filter { s -> s.login.take(1) == searchText.take(1) }
                itemsIndexed(items = listis)
                { index, item -> NamItem(movie =item)

                }

            }
        }


    }
//mvvm
//paging






}