package android.template.feature.weighbridge.ui.ticket

import com.viona.core.domain.model.Ticket
import com.viona.core.domain.util.TicketOrder

sealed class TicketWeighbridgeEvent {
    data class Order(val ticketOrder: TicketOrder): TicketWeighbridgeEvent()
    data class DeleteTicket(val ticket: Ticket): TicketWeighbridgeEvent()
    object RestoreTicket: TicketWeighbridgeEvent()
    object ToggleOrderSection: TicketWeighbridgeEvent()

}