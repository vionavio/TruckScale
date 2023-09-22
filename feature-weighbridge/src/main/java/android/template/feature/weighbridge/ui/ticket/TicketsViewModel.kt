package android.template.feature.weighbridge.ui.ticket

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viona.core.domain.model.Ticket
import com.viona.core.domain.usecase.TicketUseCase
import com.viona.core.domain.util.OrderType
import com.viona.core.domain.util.TicketOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor(
    private val ticketUseCase: TicketUseCase
) : ViewModel() {

    private val _state = mutableStateOf(TicketState())
    val state: State<TicketState> = _state

    private var recentDeletedTicket: Ticket? = null

    private var getTicketJob: Job? = null

    init {
        getTicket(TicketOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: TicketEvent) {
        when (event) {
            is TicketEvent.Order -> {
                if (state.value.ticketOrder::class == event.ticketOrder::class &&
                    state.value.ticketOrder.orderType == event.ticketOrder.orderType){
                    return
                }
                getTicket(event.ticketOrder)

            }

            is TicketEvent.DeleteTicket -> {
                viewModelScope.launch {
                    ticketUseCase.deleteTicket(event.ticket)
                    recentDeletedTicket = event.ticket
                }

            }

            is TicketEvent.RestoreTicket -> {
                viewModelScope.launch {
                    ticketUseCase.addTicket(recentDeletedTicket ?: return@launch)
                    recentDeletedTicket = null

                }

            }

            is TicketEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )

            }
        }

    }

    private fun getTicket(ticketOrder: TicketOrder) {
        getTicketJob?.cancel()

        getTicketJob = ticketUseCase.getTickets(ticketOrder)
            .onEach { tickets ->
                _state.value = state.value.copy(
                    tickets = tickets,
                    ticketOrder = ticketOrder
                )
            }
            .launchIn(viewModelScope)

    }

}