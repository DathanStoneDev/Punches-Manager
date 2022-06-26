package com.devstone.punchesmanager.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Summarize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstone.punchesmanager.util.UiEvent
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier
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
        HomeTopAppBar(modifier = modifier, onEvent = viewModel::onEvent)
        Spacer(modifier = modifier.height(15.dp))
        GreetingCard(modifier)
        Spacer(modifier = modifier.height(15.dp))
        ReportCardTile(modifier = modifier, onEvent = viewModel::onEvent)
        Spacer(modifier = modifier.height(15.dp))
        StatCardTile(modifier = modifier)

    }
}

@Composable
fun HomeTopAppBar(modifier: Modifier, onEvent: (HomeEvent) -> Unit) {
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
                        tint = Red
                    )
                }
        },
        elevation = 0.dp
    )
}

@Composable
fun GreetingCard(modifier: Modifier) {

    //make this into a util class
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
        Column () {
            Text(text = "Welcome back Dathan")
            Text(text = currentDate)
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
                fontSize = 18.sp,
            )
            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "go-to-reports")
        }
    }
}

@Composable
fun StatCardTile(modifier: Modifier) {
    Surface(
        modifier = modifier
            .width(350.dp)
            .height(200.dp)
            .padding(0.dp, 10.dp)
            .clip(CutCornerShape(10.dp))
            .background(White),
        elevation = 10.dp,
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
                ) {
            Column {
                Text(text = "25")
                Text(text = "Total Number Of Sets")
            }
        }
    }
}


