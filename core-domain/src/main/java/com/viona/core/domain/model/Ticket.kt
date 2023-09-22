package com.viona.core.domain.model

data class Ticket(
    val id: Int,
    val driverName: String,
    val timestamp: Long,
    val licenseNumber: Int,
    val inboundWeight: Double,
    val outboundWeight: Double,
    val netWeight: Double
)
