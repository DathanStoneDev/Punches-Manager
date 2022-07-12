package com.devstone.punchesmanager.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Summarize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstone.punchesmanager.util.UiEvent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.devstone.punchesmanager.util.navigation.USERNAME
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*

/**
 * HomeScreen composable that displays the HomeScreen UI. Events flow up, to the viewmodel.
 */
@Composable
fun HomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier,
){
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
            }
        }
    }


    
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
            ){
        HomeTopAppBar(onEvent = viewModel::onEvent)
        Spacer(modifier = modifier.height(15.dp))
        GreetingCard(modifier, USERNAME.username)
        Spacer(modifier = modifier.height(15.dp))
        ReportCardTile(modifier = modifier, onEvent = viewModel::onEvent)
        Spacer(modifier = modifier.height(15.dp))

    }
}

@Composable
fun HomeTopAppBar(onEvent: (HomeEvent) -> Unit) {
    TopAppBar(
        title = { Text("Home") },
        backgroundColor = LightGray,
        actions =  {
            IconButton(onClick = {
                onEvent(HomeEvent.OnProfileClick) },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "profile",
                        tint = Black
                    )
                }
        },
        elevation = 0.dp
    )
}

@Composable
fun GreetingCard(modifier: Modifier, name: String) {

    val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)
    val currentDate = sdf.format(Date())

    Surface(
        modifier = modifier
            .width(350.dp)
            .height(200.dp)
            .padding(0.dp, 10.dp)
            .clip(CutCornerShape(10.dp))
            .background(White),
        elevation = 10.dp
    ) {
        Column (
            modifier = modifier
                .padding(start = 10.dp, top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
                ) {
            Text(text = "Welcome $name", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Spacer(modifier = modifier.height(25.dp))
            Text(text = currentDate, fontFamily = FontFamily.Monospace, fontSize = 24.sp)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReportCardTile(modifier: Modifier, onEvent: (HomeEvent) -> Unit) {
    Surface(
        modifier = modifier
            .width(350.dp)
            .height(85.dp),
        elevation = 10.dp,
        color = White,
        shape = CutCornerShape(10.dp),
        onClick = {
            onEvent(HomeEvent.OnReportCardClick)
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Summarize,
                contentDescription = "report-icon",
                modifier = modifier
                    .height(50.dp)
                    .width(50.dp)
                    )
            Text(
                text = "Reports",
                modifier = modifier
                    .padding(10.dp)
                    .weight(1f),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                fontSize = 18.sp,
            )
            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "go-to-reports")
        }
    }
}


