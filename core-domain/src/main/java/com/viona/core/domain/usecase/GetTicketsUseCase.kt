package com.viona.core.domain.usecase

import android.template.core.data.TicketRepository
import com.viona.core.domain.model.Ticket
import com.viona.core.domain.util.OrderType
import com.viona.core.domain.util.TicketOrder
import com.viona.core.domain.util.toListTicket
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTicketsUseCase(
    private val repository: TicketRepository
) {

    operator fun invoke(ticketOrder: TicketOrder = TicketOrder.Date(OrderType.Descending)): Flow<List<Ticket>> {
        return repository.getTickets().map { data ->
            val tickets = data.toListTicket()
            when (ticketOrder.orderType) {
                is OrderType.Ascending -> {
                    when (ticketOrder) {
                        is TicketOrder.Date -> tickets.sortedBy { it.timestamp }
                        is TicketOrder.DriverName -> tickets.sortedBy { it.driverName.lowercase() }
                        is TicketOrder.LicenseNumber -> tickets.sortedBy { it.licenseNumber }
                    }
                }

                is OrderType.Descending -> {
                    when (ticketOrder) {
                        is TicketOrder.Date -> tickets.sortedByDescending { it.timestamp }
                        is TicketOrder.DriverName -> tickets.sortedByDescending { it.driverName.lowercase() }
                        is TicketOrder.LicenseNumber -> tickets.sortedByDescending { it.licenseNumber }
                    }
                }
            }
        }
    }
}