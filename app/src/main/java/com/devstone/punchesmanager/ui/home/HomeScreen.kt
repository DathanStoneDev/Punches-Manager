package com.devstone.punchesmanager.ui.home

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devstone.punchesmanager.util.navigation.BottomNavItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier,
    onItemClick: (BottomNavItem) -> Unit,
    viewModel: HomeViewModel
){
    BottomNavigation (
        modifier = modifier,
        backgroundColor = Color.Blue,
        elevation = 5.dp
            ){
        items.forEach { item -> 
            BottomNavigationItem(
                selected = viewModel.selectedItem,
                onClick = {
                onItemClick (item) },
                icon = {

                }

            )
        }

    }

}



