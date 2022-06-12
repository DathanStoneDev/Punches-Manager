package com.devstone.punchesmanager.ui.home

sealed class HomeEvent {
    object OnHomeClick: HomeEvent()
    object OnToolSetListClick: HomeEvent()
    object OnProductListClick: HomeEvent()
    object OnRecordsListClick: HomeEvent()
}
