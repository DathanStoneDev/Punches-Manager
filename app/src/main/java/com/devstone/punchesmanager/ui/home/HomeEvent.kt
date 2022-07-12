package com.devstone.punchesmanager.ui.home

/**
 * Events that occur on the home screen.
 */
sealed class HomeEvent {
    object OnToolSetCardClick: HomeEvent()
    object OnProductCardClick: HomeEvent()
    object OnRecordCardClick: HomeEvent()
    object OnReportCardClick: HomeEvent()
    object OnProfileClick: HomeEvent()
}
