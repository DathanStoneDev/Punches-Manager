package com.devstone.punchesmanager.util.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.devstone.punchesmanager.util.navigation.BottomNavigationBar
import com.devstone.punchesmanager.util.navigation.Navigation

@Composable
fun MainEntry(

) {
    val navController = rememberNavController()
    Scaffold(
        content = {
            Navigation(navController)
        },
        bottomBar = { BottomNavigationBar(navController = navController) },
    )
}



