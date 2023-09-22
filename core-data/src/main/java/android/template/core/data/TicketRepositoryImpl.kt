package android.template.core.data

import android.template.core.database.Ticket
import android.template.core.database.TicketDao
import kotlinx.coroutines.flow.Flow

class TicketRepositoryImpl(
    private val dao: TicketDao
) : TicketRepository {
    override fun getTickets(): Flow<List<Ticket>> {
        return dao.getTickets()
    }

    override suspend fun getTicketById(id: Int): Ticket? {
        return dao.getTicketById(id)
    }

    override suspend fun insertTicket(ticket: Ticket) {
        dao.insertTicket(ticket)
    }

    override suspend fun deleteTicket(ticket: Ticket) {
        dao.deleteTicket(ticket)
    }
}