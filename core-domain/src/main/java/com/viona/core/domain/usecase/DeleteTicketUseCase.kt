package com.viona.core.domain.usecase

import android.template.core.data.TicketRepository
import com.viona.core.domain.model.Ticket
import com.viona.core.domain.util.toTicketDBEntity


class DeleteTicketUseCase(
    private val repository: TicketRepository
) {
    suspend operator fun invoke(ticket: Ticket) {
        repository.deleteTicket(ticket.toTicketDBEntity())
    }
}