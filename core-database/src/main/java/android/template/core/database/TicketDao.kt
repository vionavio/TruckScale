package android.template.core.database

import android.template.core.database.model.TicketDBEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {

    @Query("SELECT * FROM ticketDBEntity")
    fun getTickets(): Flow<List<TicketDBEntity>>

    @Query("SELECT * FROM ticketDBEntity WHERE id = :id")
    suspend fun getTicketById(id: Int): TicketDBEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(ticket: TicketDBEntity)

    @Delete
    suspend fun deleteTicket(ticket: TicketDBEntity)

}