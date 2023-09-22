package com.viona.core.domain.di

import android.template.core.data.TicketRepository
import com.viona.core.domain.usecase.AddTicketUseCase
import com.viona.core.domain.usecase.DeleteTicketUseCase
import com.viona.core.domain.usecase.GetTicketUseCase
import com.viona.core.domain.usecase.GetTicketsUseCase
import com.viona.core.domain.usecase.TicketUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideTicketUseCase(repository: TicketRepository): TicketUseCase {
        return TicketUseCase(
            getTickets = GetTicketsUseCase(repository),
            deleteTicket = DeleteTicketUseCase(repository),
            addTicket = AddTicketUseCase(repository),
            getTicket = GetTicketUseCase(repository)
        )
    }
}
