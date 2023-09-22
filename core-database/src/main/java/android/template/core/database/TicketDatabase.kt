package android.template.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Ticket::class],
    version = 1
)
abstract class TicketDatabase: RoomDatabase() {
    abstract val ticketDao: TicketDao

    companion object {
        const val DATABASE_NAME = "ticket.db"
    }
}