package android.template.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TicketDBEntity(
    val driverName: String,
    val timestamp: String,
    val licenseNumber: Long,
    val inboundWeight: Int,
    val outboundWeight: Int,
    val netWeight: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int
)