package com.viona.core.domain.usecase

import com.viona.core.domain.model.Ticket
import com.viona.core.domain.util.FakeTicketRepository
import io.mockk.clearAllMocks
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AddTicketUseCaseTest {
    private lateinit var addTicket: AddTicketUseCase
    private lateinit var fakeRepository: FakeTicketRepository

    @Before
    fun setUp() {
        fakeRepository = mockk<FakeTicketRepository>(relaxed = true)
        addTicket = AddTicketUseCase(fakeRepository)
    }

    @Test
    fun `Add ticket use case`() = runBlocking {
        // Given
        val ticket = Ticket(
            driverName = "vio", timestamp = "12", licenseNumber = 123,
            inboundWeight = 23, outboundWeight = 10, netWeight = 13, id = 1
        )
        // When
        val result = addTicket(ticket)

        // Then
        assertEquals(Unit, result)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}