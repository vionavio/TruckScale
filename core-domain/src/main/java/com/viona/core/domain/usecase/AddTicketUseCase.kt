package com.viona.core.domain.usecase

import android.template.core.data.TicketRepository
import com.viona.core.domain.model.Ticket
import com.viona.core.domain.util.InvalidException
import com.viona.core.domain.util.toTicketDBEntity


class AddTicketUseCase(
    private val repository: TicketRepository
) {
    suspend operator fun invoke(ticket: Ticket){
        if (ticket.driverName.isBlank()) {
            throw InvalidException("The Driver Name cant be empty")
        }
        if (ticket.licenseNumber == 0) {
            throw InvalidException("The License Number cant be empty")
        }

        repository.insertTicket(ticket.toTicketDBEntity())
    }
}