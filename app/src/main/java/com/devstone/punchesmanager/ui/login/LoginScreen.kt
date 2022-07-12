package com.devstone.punchesmanager.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstone.punchesmanager.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun LoginScreen (
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ProfileAndLoginViewModel = hiltViewModel(),
    modifier: Modifier,
){

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "PUNCHES MANAGER",
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(75.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,) {
            Surface(
                modifier = modifier
                    .width(250.dp)
                    .height(55.dp),
                elevation = 10.dp,
                color = Color.White,
                shape = CutCornerShape(10.dp)
            ) {
                TextField(
                    value = viewModel.username,
                    onValueChange = {
                        viewModel.onLogin(LoginEvent.OnUsernameEntry(it)
                        )
                    },
                    label = {
                        Text(
                            text = "Username",
                            fontFamily = FontFamily.Monospace
                        )
                    },
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.White
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                modifier = modifier
                    .width(250.dp)
                    .height(55.dp),
                elevation = 10.dp,
                color = Color.White,
                shape = CutCornerShape(10.dp)
            ) {
                TextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.onLogin(LoginEvent.OnPasswordEntry(it)) },
                    label = {
                        Text(
                            text = "Password",
                            fontFamily = FontFamily.Monospace
                        )
                    },
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.White
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(onClick = { viewModel.onLogin(LoginEvent.OnLoginClick(viewModel.username, viewModel.password)) }) {
                Text(text = "Login")
            }
        }

    }
}