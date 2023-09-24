package android.template.feature.weighbridge.di

import android.template.core.data.TicketRepository
import android.template.core.database.model.TicketDBEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTicketRepository : TicketRepository {

    private val tickets = mutableListOf<TicketDBEntity>()

    override fun getTickets(): Flow<List<TicketDBEntity>> {
        return flow { emit(tickets) }
    }

    override suspend fun getTicketById(id: Int): TicketDBEntity? {
        return tickets.find { it.id == id }
    }

    override suspend fun insertTicket(ticket: TicketDBEntity) {
        tickets.add(ticket)
    }

    override suspend fun deleteTicket(ticket: TicketDBEntity) {
        tickets.remove(ticket)
    }
}