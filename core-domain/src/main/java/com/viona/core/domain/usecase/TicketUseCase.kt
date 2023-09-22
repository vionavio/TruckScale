package com.viona.core.domain.usecase

data class TicketUseCase(
    val getTickets: GetTicketsUseCase,
    val deleteTicket: DeleteTicketUseCase,
    val addTicket: AddTicketUseCase,
    val getTicket: GetTicketUseCase
)