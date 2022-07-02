package com.devstone.punchesmanager.util.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainEntry(

) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        content = {
            Navigation(navController)
        },
        bottomBar = {
            if(navBackStackEntry?.destination?.route != "login") {
                BottomNavigationBar(navController = navController)
            }
             },
    )
}



