package com.viona.core.domain.util

import android.template.core.database.model.TicketDBEntity
import com.viona.core.domain.model.Ticket


fun Ticket.toTicketDBEntity(): TicketDBEntity {
    return TicketDBEntity(
        id = this.id,
        timestamp = this.timestamp,
        licenseNumber = this.licenseNumber,
        inboundWeight = this.inboundWeight,
        outboundWeight = this.outboundWeight,
        netWeight = this.netWeight,
        driverName = this.driverName,
    )
}

fun TicketDBEntity.toTicket(): Ticket {
    return Ticket(
        id = this.id ?: 0,
        driverName = this.driverName,
        timestamp = this.timestamp,
        licenseNumber = this.licenseNumber,
        inboundWeight = this.inboundWeight,
        outboundWeight = this.outboundWeight,
        netWeight = this.netWeight
    )
}

fun List<TicketDBEntity>.toListTicket(): List<Ticket> {
    return this.map {
        it.toTicket()
    }
}