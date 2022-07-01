package com.devstone.punchesmanager.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstone.punchesmanager.data.repository.UserRepository
import com.devstone.punchesmanager.util.UiEvent
import com.devstone.punchesmanager.util.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileAndLoginViewModel @Inject constructor(
     val repository: UserRepository
): ViewModel() {

     private val _uiEvent = Channel<UiEvent>()
     val uiEvent = _uiEvent.receiveAsFlow()

     var username by mutableStateOf("")
          private set

     fun onLogin(event: LoginEvent) {
          when(event) {
               is LoginEvent.OnLoginClick -> {
                    viewModelScope.launch {
                         try {
                              repository.getUser(event.username, event.password).let { user ->
                                   username = user.username
                                   sendUiEvent(UiEvent.Navigate(Routes.HOME))
                              }
                         } catch (ex: NullPointerException) {
                              println("User Not Found")
                         }
                    }
               }
          }
     }


     private fun sendUiEvent(event: UiEvent) {
          viewModelScope.launch {
               _uiEvent.send(event)
          }
     }
}