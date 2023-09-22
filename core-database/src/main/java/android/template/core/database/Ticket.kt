package android.template.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class Ticket(

    @PrimaryKey val id: Int? = null,

    val timestamp: Long,
    val licenseNumber: Int,
    val inboundWeight: Double,
    val outboundWeight: Double,
    val netWeight: Double
)

@Dao
interface TicketDao {

    @Query("SELECT * FROM ticket")
    fun getTicket(): Flow<List<Ticket>>

    @Query("SELECT * FROM ticket WHERE id = :id")
    suspend fun getTicketById(id: Int): Ticket?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(ticket: Ticket)

    @Delete
    suspend fun deleteTicket(ticket: Ticket)

}