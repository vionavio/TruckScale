package android.template.feature.weighbridge.ui.ticket

import com.viona.core.domain.model.Ticket
import com.viona.core.domain.util.TicketOrder

sealed class TicketEvent {
    data class Order(val ticketOrder: TicketOrder): TicketEvent()
    data class DeleteTicket(val ticket: Ticket): TicketEvent()
    object RestoreTicket: TicketEvent()
    object ToggleOrderSection: TicketEvent()

}