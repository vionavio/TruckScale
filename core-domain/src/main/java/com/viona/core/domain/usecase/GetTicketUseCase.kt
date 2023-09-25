package com.viona.core.domain.usecase

import android.template.core.data.TicketRepository
import com.viona.core.domain.model.Ticket
import com.viona.core.domain.util.toTicket

class GetTicketUseCase(
    private val repository: TicketRepository
) {
    suspend operator fun invoke(id: String): Ticket? {
        return repository.getTicketById(id)?.toTicket()
    }
}