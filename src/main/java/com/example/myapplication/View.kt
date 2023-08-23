package com.example.myapplication

import android.content.ClipboardManager.OnPrimaryClipChangedListener
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField

import androidx.compose.material.Card
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.ui.theme.md_theme_dark_inversePrimary
import com.example.myapplication.ui.theme.md_theme_dark_secondary
import com.example.myapplication.ui.theme.md_theme_dark_tertiary
import com.example.myapplication.ui.theme.md_theme_light_error
import com.example.myapplication.ui.theme.md_theme_light_primary
import com.example.myapplication.ui.theme.md_theme_light_secondaryContainer

var data: List<gitUser> by mutableStateOf(listOf())
fun dataa(dataFromMain:List<gitUser>) {
    data=dataFromMain

}



@Composable
fun TopBar() {

    Box(
        modifier = Modifier.fillMaxWidth()

    ) {
        Row {


            Image(
                painter = painterResource(id = R.drawable.vodafone),
                contentDescription = null, // decorative element,
                modifier = Modifier.clip(CircleShape)

            )


            TopAppBar(
                title = {
                    Text("_Vois")

                },

                backgroundColor = Color.White,

                contentColor = Color.Black,


                )


        }

    }
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
        NamesOfGitList(textVal, nameList = data)

    }
}


@Composable
fun searchNamesList(textVal: MutableState<TextFieldValue>) {
    TextField(
        value = textVal.value,
        onValueChange = { textVal.value = it },
        placeholder = { Text(text = "Search  Name", color = Color.White) },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(Color.White, fontSize = 20.sp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                Modifier
                    .padding(15.dp)
                    .size(30.dp)
            )
        },
        trailingIcon = {
            if (textVal.value != TextFieldValue("")) {
                androidx.compose.material.IconButton(onClick = {
                    textVal.value = TextFieldValue("")
                }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close, contentDescription = "Close",
                        Modifier
                            .padding(15.dp)
                            .size(27.dp)

                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,

            cursorColor = Color.Black,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = md_theme_light_primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent


        )
    )
}


@Composable
fun NamesOfGitList(textVal: MutableState<TextFieldValue>, nameList: List<gitUser>) {

    val searchText = textVal.value.text




    LazyColumn {
        if (searchText.isEmpty()) {
            itemsIndexed(items = nameList) { index, item ->
                NamItem(names = item)
            }
        } else {

            val listis: List<gitUser> =
                nameList.filter { s -> s.login.take(1) == searchText.take(1) }
            itemsIndexed(items = listis)
            { index, item ->
                NamItem(names = item)

            }

        }
    }


}







@Composable
        fun NamItem(names: gitUser) {
            Card(
                modifier = Modifier
                    .padding(10.dp, 6.dp)
                    .fillMaxWidth()
                    .height(110.dp),
                shape = RoundedCornerShape(25.dp),
                elevation = 15.dp

                )

            {
                Surface() {

                    Row(
                        Modifier
                            .padding(6.dp)
                            .fillMaxSize()
                    ) {

                        AsyncImage(

                            model = names.avatar_url,
                            contentDescription = null ,
                            modifier = Modifier.clip(CircleShape)

                        )




                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxHeight()
                                .weight(0.6f)
                        ) {
                            Text(
                                text = names.login,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Bold




                            )

                            Text(
                                text = names.login,
                                style = MaterialTheme.typography.titleSmall,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )

                        }
                    }
                }
            }

        }




