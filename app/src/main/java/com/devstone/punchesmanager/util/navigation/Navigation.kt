package com.devstone.punchesmanager.util.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devstone.punchesmanager.ui.home.HomeScreen
import com.devstone.punchesmanager.ui.product.ProductAddEditScreen
import com.devstone.punchesmanager.ui.product.ProductListScreen
import com.devstone.punchesmanager.ui.record.RecordAddEditScreen
import com.devstone.punchesmanager.ui.record.RecordListScreen
import com.devstone.punchesmanager.ui.toolset.ToolSetAddEditScreen
import com.devstone.punchesmanager.ui.toolset.ToolSetListScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                onNavigate = {
                    navController.navigate(it.route)
                },
                modifier = Modifier)
        }
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
        composable(
            route = Routes.RECORD_ADD_EDIT + "?PONumber={PONumber}",
            arguments = listOf(
                navArgument(name = "PONumber") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            RecordAddEditScreen(onPopBackStack = {
                navController.popBackStack()
            }, modifier = Modifier)
        }
        composable(
            route = Routes.RECORD_ADD_EDIT + "?toolRecordId={toolRecordId}",
            arguments = listOf(
                navArgument(name = "tooRecordId") {
                    type = NavType.LongType
                    defaultValue = 0
                }
            )
        ) {
            RecordAddEditScreen(onPopBackStack = {
                navController.popBackStack()
            }, modifier = Modifier)
        }
        composable(Routes.PRODUCT_LIST) {
            ProductListScreen(onNavigate = {
                navController.navigate(it.route)
            })
        }
        composable(Routes.RECORD_LIST) {
            RecordListScreen(onNavigate = {
                navController.navigate(it.route)
            })
        }
        composable(
            route = Routes.PRODUCT_ADD_EDIT + "?productId={productId}",
            arguments = listOf(
                navArgument(name = "productId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            ProductAddEditScreen(onPopBackStack = {
                navController.popBackStack()
            })
        }
    }
}