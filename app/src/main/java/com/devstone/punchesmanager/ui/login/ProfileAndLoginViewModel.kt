package com.devstone.punchesmanager.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstone.punchesmanager.data.repository.UserRepository
import com.devstone.punchesmanager.util.UiEvent
import com.devstone.punchesmanager.util.navigation.Routes
import com.devstone.punchesmanager.util.navigation.USERNAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI and Business logic for the Login and Profile pages.
 */
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

     private var hashedPassword = ""



     //Initializing savedUserName for Profile and Home Screen.
     init {
          val savedUserName = savedStateHandle.get<String>("username")
          if (savedUserName != null) {
               username = savedUserName
          }
     }

     //On login, checks hashed password in database with entered password.
     fun onLogin(event: LoginEvent) {
          when(event) {
               is LoginEvent.OnLoginClick -> {
                    viewModelScope.launch {
                         try {
                              repository.getUserPasswordForVerification(event.username).let { hash ->
                                   hashedPassword = hash
                                   if (checkPassword(password, hashedPassword)) {
                                        username = event.username
                                        USERNAME.username = username
                                        sendUiEvent(UiEvent.Navigate(Routes.HOME + "?username=${username}"))
                                   } else {
                                        println("Wrong Passy")
                                   }
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

     /**
      * Encrypts passwords upon creating a user.
      * Uses Bcrypt to encrypt password with a salt added.
      */
     private fun encryptPassword(pass: String): String {
          return org.mindrot.jbcrypt.BCrypt.hashpw(pass, org.mindrot.jbcrypt.BCrypt.gensalt(12))
     }

     /**
      * Verification check for entered password vs saved password.
      */
     private fun checkPassword(entered: String, inDatabase: String): Boolean {
          return org.mindrot.jbcrypt.BCrypt.checkpw(entered, inDatabase)
     }

}