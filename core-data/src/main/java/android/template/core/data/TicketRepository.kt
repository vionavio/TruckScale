package android.template.core.data

import android.template.core.database.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketRepository {
    fun getTickets(): Flow<List<Ticket>>

    suspend fun getTicketById(id: Int): Ticket?

    suspend fun insertTicket(ticket: Ticket)

    suspend fun deleteTicket(ticket: Ticket)
}