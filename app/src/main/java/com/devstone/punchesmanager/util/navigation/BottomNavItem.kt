package com.devstone.punchesmanager.util.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Simple data class that holds bottom navigation data parameters.
 */
data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)
