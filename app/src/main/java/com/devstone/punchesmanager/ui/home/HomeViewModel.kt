package com.devstone.punchesmanager.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ViewModel()

{

    val selectedItem by mutableStateOf(false)
    val items = listOf<String>("Home", "Tool Sets", "Products", "Records")



}