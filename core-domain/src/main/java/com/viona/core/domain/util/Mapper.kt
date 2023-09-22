package com.viona.core.domain.util

import android.template.core.database.model.TicketDBEntity
import com.viona.core.domain.model.Ticket


fun Ticket.toTicketDBEntity(): android.template.core.database.model.TicketDBEntity {
    return android.template.core.database.model.TicketDBEntity(
        id = this.id,
        timestamp = this.timestamp,
        licenseNumber = this.licenseNumber,
        inboundWeight = this.inboundWeight,
        outboundWeight = this.outboundWeight,
        netWeight = this.netWeight,
        driverName = this.driverName,
    )
}

fun android.template.core.database.model.TicketDBEntity.toTicket(): Ticket {
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

fun List<android.template.core.database.model.TicketDBEntity>.toListTicket(): List<Ticket> {
    return this.map {
        it.toTicket()
    }
}