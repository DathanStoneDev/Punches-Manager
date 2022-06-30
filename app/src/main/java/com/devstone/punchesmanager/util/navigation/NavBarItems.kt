package com.devstone.punchesmanager.util.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory2

object NavBarItems {
    val bottomNavItem = listOf(
        BottomNavItem(
            name = "home",
            route = Routes.HOME,
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            name = "tool sets",
            route = Routes.TOOL_SET_LIST,
            icon = Icons.Filled.Build
        ),
        BottomNavItem(
            name = "products",
            route = Routes.PRODUCT_LIST,
            icon = Icons.Filled.Inventory2
        ),
        BottomNavItem(
            name = "records",
            route = Routes.RECORD_LIST,
            icon = Icons.Filled.Description
        )
    )
}