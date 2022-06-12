package com.devstone.punchesmanager.util.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home

object NavBarItems {
    val bottomNavItem = listOf(
        BottomNavItem(
            name = "home",
            route = Routes.HOME,
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            name = "toolsets",
            route = Routes.TOOL_SET_LIST,
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            name = "products",
            route = Routes.PRODUCT_LIST,
            icon = Icons.Default.Home
        )
    )
}