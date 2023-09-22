package android.template.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TicketDBEntity(

    @PrimaryKey val id: Int? = null,

    val driverName: String,
    val timestamp: Long,
    val licenseNumber: Int,
    val inboundWeight: Double,
    val outboundWeight: Double,
    val netWeight: Double
)