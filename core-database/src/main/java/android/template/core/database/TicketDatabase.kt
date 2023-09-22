package android.template.core.database

import android.template.core.database.model.TicketDBEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TicketDBEntity::class],
    version = 1
)
abstract class TicketDatabase: RoomDatabase() {
    abstract val ticketDao: TicketDao

    companion object {
        const val DATABASE_NAME = "ticket.db"
    }
}