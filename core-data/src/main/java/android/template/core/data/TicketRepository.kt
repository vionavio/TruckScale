package android.template.core.data

import android.template.core.database.model.TicketDBEntity
import kotlinx.coroutines.flow.Flow

interface TicketRepository {
    fun getTickets(): Flow<List<TicketDBEntity>>

    suspend fun getTicketById(id: String): TicketDBEntity?

    suspend fun insertTicket(ticket: TicketDBEntity)

    suspend fun deleteTicket(ticket: TicketDBEntity)
}