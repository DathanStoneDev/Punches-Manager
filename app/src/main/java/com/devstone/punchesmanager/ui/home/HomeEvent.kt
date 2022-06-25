package com.devstone.punchesmanager.ui.home

sealed class HomeEvent {
    object OnToolSetCardClick: HomeEvent()
    object OnProductCardClick: HomeEvent()
    object OnRecordCardClick: HomeEvent()
    object OnReportCardClick: HomeEvent()
    object OnProfileClick: HomeEvent()
}
