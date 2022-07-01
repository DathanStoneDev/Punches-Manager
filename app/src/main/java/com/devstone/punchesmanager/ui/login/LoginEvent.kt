package com.devstone.punchesmanager.ui.login

sealed class LoginEvent {
    data class OnLoginClick(val username: String, val password: String): LoginEvent()
}
