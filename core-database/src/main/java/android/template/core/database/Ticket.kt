package android.template.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ticket(

    @PrimaryKey val id: Int? = null,

    val timestamp: Long,
    val licenseNumber: Int,
    val inboundWeight: Double,
    val outboundWeight: Double,
    val netWeight: Double
)