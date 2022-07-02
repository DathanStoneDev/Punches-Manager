package com.devstone.punchesmanager.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstone.punchesmanager.MainActivity
import com.devstone.punchesmanager.data.repository.UserRepository
import com.devstone.punchesmanager.util.UiEvent
import com.devstone.punchesmanager.util.navigation.Routes
import com.devstone.punchesmanager.util.navigation.USERNAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileAndLoginViewModel @Inject constructor(
     val repository: UserRepository,
     savedStateHandle: SavedStateHandle,
): ViewModel() {

     private val _uiEvent = Channel<UiEvent>()
     val uiEvent = _uiEvent.receiveAsFlow()


     var username by  mutableStateOf("")
          private set

     var password by mutableStateOf("")
          private set

     init {
          val savedUserName = savedStateHandle.get<String>("username")
          if (savedUserName != null) {
               username = savedUserName
          }
     }

     fun onLogin(event: LoginEvent) {
          when(event) {
               is LoginEvent.OnLoginClick -> {
                    viewModelScope.launch {
                         try {
                              repository.getUser(event.username, event.password).let { user ->
                                   username = user.username
                                   USERNAME.username = username
                                   sendUiEvent(UiEvent.Navigate(Routes.HOME + "?username=${username}"))
                              }
                         } catch (ex: NullPointerException) {
                              println("User Not Found")
                         }
                    }
               }
               is LoginEvent.OnUsernameEntry -> {
                    username = event.username
               }
               is LoginEvent.OnPasswordEntry -> {
                    password = event.password
               }
          }
     }


     private fun sendUiEvent(event: UiEvent) {
          viewModelScope.launch {
               _uiEvent.send(event)
          }
     }
}