package com.example.myapplication

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Network.ApiCall
import kotlinx.coroutines.launch


class GitViewModel: ViewModel() {

    companion object {
        @SuppressLint("SuspiciousIndentation")
        fun getNameList():List<gitUser> {
            var NamesListResponce: List<gitUser> by mutableStateOf(listOf())

                    val DataTobeSendForView = ApiCall.getsdata()

                    //NamesListResponce = DataTobeSendForView

                   // MyView.TakingListFromViewModel(NamesListResponce)
              return NamesListResponce
            }
        }


    }

