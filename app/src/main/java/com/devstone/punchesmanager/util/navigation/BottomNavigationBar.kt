package com.devstone.punchesmanager.util.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route


        NavBarItems.bottomNavItem.forEach { navItem ->
            BottomNavigationItem(selected = currentRoute == navItem.route, onClick = {
                navController.navigate(navItem.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            },
                icon = {
                    Icon(imageVector = navItem.icon,
                        contentDescription = navItem.name)
                },
                label = {
                    Text(text = navItem.name)
                },
            )
        }
    }
}