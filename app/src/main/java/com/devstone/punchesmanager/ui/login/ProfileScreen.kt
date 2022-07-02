package com.devstone.punchesmanager.ui.login


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstone.punchesmanager.MainActivity
import com.devstone.punchesmanager.util.navigation.USERNAME


@Composable
fun ProfileScreen (
    modifier: Modifier
){

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
            )
    {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "profile icon",
            modifier = modifier
                .width(200.dp)
                .height(200.dp),
            tint = Color.White
        )
        Spacer(modifier = modifier.height(20.dp))
        Surface(
            modifier = modifier
                .width(350.dp)
                .height(55.dp),
            color = Color.White,
            shape = CutCornerShape(10.dp)
        ) {
            Column(modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "User name: ${USERNAME.username}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 2.sp,
                )
            }
        }

    }
}