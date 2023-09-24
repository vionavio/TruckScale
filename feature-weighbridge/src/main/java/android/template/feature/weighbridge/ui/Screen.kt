package android.template.feature.weighbridge.ui

sealed class Screen(val route: String) {
    object TicketsScreen: Screen("tickets_screen")
    object AddEditTicketScreen: Screen("add_edit_ticket_screen")
}