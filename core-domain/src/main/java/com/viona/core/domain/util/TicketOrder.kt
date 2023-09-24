package com.viona.core.domain.util

sealed class TicketOrder(val orderType: OrderType) {

    class Date(orderType: OrderType): TicketOrder(orderType)
    class DriverName(orderType: OrderType): TicketOrder(orderType)
    class LicenseNumber(orderType: OrderType): TicketOrder(orderType)

    fun copy(orderType: OrderType): TicketOrder {
        return when(this) {
            is DriverName -> DriverName(orderType)
            is Date -> Date(orderType)
            is LicenseNumber -> LicenseNumber(orderType)
        }
    }
}