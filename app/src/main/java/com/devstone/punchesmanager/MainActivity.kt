package com.devstone.punchesmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devstone.punchesmanager.ui.theme.PunchesManagerTheme
import com.devstone.punchesmanager.ui.toolset.ToolSetAddEditScreen
import com.devstone.punchesmanager.ui.toolset.ToolSetListScreen
import com.devstone.punchesmanager.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PunchesManagerTheme {
               val navController = rememberNavController()
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
        }
    }
}
