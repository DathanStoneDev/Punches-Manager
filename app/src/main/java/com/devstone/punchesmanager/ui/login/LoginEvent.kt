package com.devstone.punchesmanager.ui.login

/**
 * Contains login page events.
 */
sealed class LoginEvent {
    data class OnLoginClick(val username: String, val password: String): LoginEvent()
    data class OnUsernameEntry(val username: String): LoginEvent()
    data class OnPasswordEntry(val password: String): LoginEvent()
}
