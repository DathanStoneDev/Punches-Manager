package com.devstone.punchesmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devstone.punchesmanager.ui.theme.PunchesManagerTheme
import com.devstone.punchesmanager.util.navigation.NavBarItems
import com.devstone.punchesmanager.util.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PunchesManagerTheme {
                val navController = rememberNavController()
                Scaffold (
                    content = { Navigation(navController) },
                    bottomBar = { BottomNavigationBar(navController = navController) },
                )
            }
        }
    }
}

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
                    restoreState = true
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
