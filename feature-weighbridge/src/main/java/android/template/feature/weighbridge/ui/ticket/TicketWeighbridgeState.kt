package android.template.feature.weighbridge.ui.ticket

import com.viona.core.domain.model.Ticket
import com.viona.core.domain.util.OrderType
import com.viona.core.domain.util.TicketOrder

data class TicketWeighbridgeState(
    val tickets: List<Ticket> = emptyList(),
    val ticketOrder: TicketOrder = TicketOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)