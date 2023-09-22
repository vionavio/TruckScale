package com.viona.core.domain.util

sealed class TicketOrder(val orderType: OrderType) {

    class Date(orderType: OrderType): TicketOrder(orderType)
    class DriverName(orderType: OrderType): TicketOrder(orderType)
    class LicenseNumber(orderType: OrderType): TicketOrder(orderType)
}