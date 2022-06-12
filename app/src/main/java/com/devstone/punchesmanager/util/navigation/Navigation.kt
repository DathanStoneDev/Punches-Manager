package com.devstone.punchesmanager.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devstone.punchesmanager.ui.toolset.ToolSetAddEditScreen
import com.devstone.punchesmanager.ui.toolset.ToolSetListScreen
import com.devstone.punchesmanager.util.navigation.Routes

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.TOOL_SET_LIST) {
        composable(Routes.TOOL_SET_LIST) {
            ToolSetListScreen(onNavigate = {
                navController.navigate(it.route)
            })
        }
        composable(
            route = Routes.ADD_EDIT_TOOL_SET + "?PONumber={PONumber}",
            arguments = listOf(
                navArgument(name = "PONumber") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            ToolSetAddEditScreen(onPopBackStack = {
                navController.popBackStack()
            })
        }
    }
}