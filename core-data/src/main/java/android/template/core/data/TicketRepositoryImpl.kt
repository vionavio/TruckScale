package android.template.core.data

import android.template.core.database.model.TicketDBEntity
import android.template.core.database.TicketDao
import kotlinx.coroutines.flow.Flow

class TicketRepositoryImpl(
    private val dao: TicketDao
) : TicketRepository {
    override fun getTickets(): Flow<List<TicketDBEntity>> {
        return dao.getTickets()
    }

    override suspend fun getTicketById(id: Int): TicketDBEntity? {
        return dao.getTicketById(id)
    }

    override suspend fun insertTicket(ticket: TicketDBEntity) {
        dao.insertTicket(ticket)
    }

    override suspend fun deleteTicket(ticket: TicketDBEntity) {
        dao.deleteTicket(ticket)
    }
}