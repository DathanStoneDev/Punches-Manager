package com.devstone.punchesmanager.util

/**
 * Common UI Events across multiple screens.
 */
sealed class UiEvent {
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
}