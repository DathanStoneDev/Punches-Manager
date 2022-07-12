package com.devstone.punchesmanager.ui.report

/**
 * Contains events for reports.
 */
sealed class ReportEvent {
    object OnClickDosingReport: ReportEvent()
    object OnClickProductReport: ReportEvent()
    object OnClickLifeSpanReport: ReportEvent()
    data class OnSearch(val text: String): ReportEvent()
}
