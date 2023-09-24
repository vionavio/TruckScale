package com.viona.core.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.viona.core.domain.model.Ticket
import com.viona.core.domain.util.FakeTicketRepository
import com.viona.core.domain.util.OrderType
import com.viona.core.domain.util.TicketOrder
import com.viona.core.domain.util.toTicketDBEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetTicketsUseCaseTest {

    private lateinit var getTickets: GetTicketsUseCase
    private lateinit var fakeRepository: FakeTicketRepository

    @Before
    fun setUp() {
        fakeRepository = FakeTicketRepository()
        getTickets = GetTicketsUseCase(fakeRepository)

        val ticketsToInsert = mutableListOf<Ticket>()
        ('a'..'z').forEachIndexed { index, c ->
            ticketsToInsert.add(
                Ticket(
                    id = index,
                    driverName = c.toString(),
                    timestamp = c.toString(),
                    licenseNumber = c.toLong(),
                    inboundWeight = c.toInt(),
                    outboundWeight = c.toInt(),
                    netWeight = c.toInt()
                )
            )
        }
        ticketsToInsert.shuffle()
        runBlocking {
            ticketsToInsert.forEach { fakeRepository.insertTicket(it.toTicketDBEntity()) }
        }
    }

    @Test
    fun `Order tickets by driver name ascending, correct order`() = runBlocking {
        val tickets = getTickets(TicketOrder.DriverName(OrderType.Ascending)).first()

        for(i in 0..tickets.size - 2) {
            assertThat(tickets[i].driverName).isLessThan(tickets[i+1].driverName)
        }
    }

    @Test
    fun `Order tickets by driver name descending, correct order`() = runBlocking {
        val tickets = getTickets(TicketOrder.DriverName(OrderType.Descending)).first()

        for(i in 0..tickets.size - 2) {
            assertThat(tickets[i].driverName).isGreaterThan(tickets[i+1].driverName)
        }
    }

    @Test
    fun `Order tickets by license ascending, correct order`() = runBlocking {
        val tickets = getTickets(TicketOrder.LicenseNumber(OrderType.Ascending)).first()

        for(i in 0..tickets.size - 2) {
            assertThat(tickets[i].licenseNumber).isLessThan(tickets[i+1].licenseNumber)
        }
    }

    @Test
    fun `Order tickets by license descending, correct order`() = runBlocking {
        val tickets = getTickets(TicketOrder.LicenseNumber(OrderType.Descending)).first()

        for(i in 0..tickets.size - 2) {
            assertThat(tickets[i].licenseNumber).isGreaterThan(tickets[i+1].licenseNumber)
        }
    }

    @Test
    fun `Order tickets by date ascending, correct order`() = runBlocking {
        val tickets = getTickets(TicketOrder.Date(OrderType.Ascending)).first()

        for(i in 0..tickets.size - 2) {
            assertThat(tickets[i].timestamp).isLessThan(tickets[i+1].timestamp)
        }
    }

    @Test
    fun `Order tickets by date descending, correct order`() = runBlocking {
        val tickets = getTickets(TicketOrder.Date(OrderType.Descending)).first()

        for(i in 0..tickets.size - 2) {
            assertThat(tickets[i].timestamp).isGreaterThan(tickets[i+1].timestamp)
        }
    }

}