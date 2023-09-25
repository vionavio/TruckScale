package com.viona.core.domain.model

data class Ticket(
    val id: String,
    val driverName: String,
    val timestamp: String,
    val licenseNumber: Long,
    val inboundWeight: Int,
    val outboundWeight: Int,
    val netWeight: Int
)
