package com.viona.core.domain.usecase

import android.template.core.database.model.TicketDBEntity
import com.viona.core.domain.util.FakeTicketRepository
import com.viona.core.domain.util.toTicket
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetTicketUseCaseTest {

    private lateinit var getTicket: GetTicketUseCase
    private lateinit var fakeRepository: FakeTicketRepository

    @Before
    fun setUp() {
        fakeRepository = mockk<FakeTicketRepository>(relaxed = true)
        getTicket = GetTicketUseCase(fakeRepository)
    }

    @Test
    fun `Get detail ticket use case`() = runBlocking {
        // Given
        val expectedNote = TicketDBEntity(
            driverName = "vio", timestamp = "12", licenseNumber = 123,
            inboundWeight = 23, outboundWeight = 10, netWeight = 13, id = 1
        )
        val noteId = 1

        coEvery { fakeRepository.getTicketById(noteId) } returns expectedNote

        // When
        val result = getTicket(noteId)

        // Then
        assertEquals(expectedNote.toTicket(), result)
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }
}