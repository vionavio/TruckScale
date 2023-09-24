package android.template.feature.weighbridge.ui.add_edit_ticket

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viona.core.domain.model.Ticket
import com.viona.core.domain.usecase.TicketUseCase
import com.viona.core.domain.util.InvalidException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AddEditTicketViewModel @Inject constructor(
    private val ticketUseCase: TicketUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _driverName = mutableStateOf(
        TicketTextFieldState(
            title = "Driver Name :",
            hint = "Enter Driver Name..."
        )
    )
    val driverName: State<TicketTextFieldState> = _driverName

    private val _licenseNumber = mutableStateOf(
        TicketTextFieldState(
            title = "License Number :",
            hint = "Enter license number .."
        )
    )
    val licenseNumber: State<TicketTextFieldState> = _licenseNumber

    //convertTimestampToDateString(System.currentTimeMillis())
    private var _timestamp = mutableStateOf<String>(convertTimestampToDateString(System.currentTimeMillis()))
    val timestamp: MutableState<String> = _timestamp

    private val _inboundWeight = mutableStateOf(
        TicketTextFieldState(
            title = "Inbound Weight (ton):",
            hint = "Enter inbound weight .."
        )
    )
    val inboundWeight: State<TicketTextFieldState> = _inboundWeight

    private val _outboundWeight = mutableStateOf(
        TicketTextFieldState(
            title = "Outbound Weight (ton):",
            hint = "Enter outbound weight .."
        )
    )
    val outboundWeight: State<TicketTextFieldState> = _outboundWeight

    private val _netWeight = mutableStateOf(
        TicketTextFieldState(
            title = "Net Weight :",
        )
    )
    val netWeight: State<TicketTextFieldState> = _netWeight


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTicketId: Int? = null

    init {
        savedStateHandle.get<Int>("ticketId")?.let { ticketId ->
            if (ticketId != -1) {
                viewModelScope.launch {
                    ticketUseCase.getTicket(ticketId)?.also { ticket ->
                        currentTicketId = ticket.id
                        _driverName.value = driverName.value.copy(
                            text = ticket.driverName,
                            isHintVisible = false
                        )
                        _licenseNumber.value = licenseNumber.value.copy(
                            text = ticket.licenseNumber.toString(),
                            isHintVisible = false
                        )
                        _timestamp.value = ticket.timestamp
                        _inboundWeight.value = inboundWeight.value.copy(
                            text = ticket.inboundWeight.toString(),
                            isHintVisible = false
                        )
                        _outboundWeight.value = outboundWeight.value.copy(
                            text = ticket.outboundWeight.toString(),
                            isHintVisible = false
                        )
                        _netWeight.value = netWeight.value.copy(
                            text = ticket.netWeight.toString()
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTicketEvent) {
        when (event) {
            is AddEditTicketEvent.EnteredDriverName -> {
                _driverName.value = driverName.value.copy(
                    text = event.value
                )
            }

            is AddEditTicketEvent.ChangeDriverNameFocus -> {
                _driverName.value = driverName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            driverName.value.text.isBlank()
                )
            }

            is AddEditTicketEvent.EnteredLicenseNumber -> {
                _licenseNumber.value = licenseNumber.value.copy(
                    text = event.value.toString()
                )
            }

            is AddEditTicketEvent.ChangeLicenseNumberFocus -> {
                _licenseNumber.value = licenseNumber.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            licenseNumber.value.text.isBlank()
                )
            }

            is AddEditTicketEvent.EnteredInboundWeight -> {
                _inboundWeight.value = inboundWeight.value.copy(
                    text = event.value.toString()
                )
            }

            is AddEditTicketEvent.ChangeInboundWeightFocus -> {
                _inboundWeight.value = inboundWeight.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            inboundWeight.value.text.isBlank()
                )
            }

            is AddEditTicketEvent.EnteredOutboundWeight -> {
                _outboundWeight.value = outboundWeight.value.copy(
                    text = event.value.toString()
                )

            }
            is AddEditTicketEvent.NetWeight -> {
                _netWeight.value = netWeight.value.copy(
                    text = event.value.toString()
                )
            }

            is AddEditTicketEvent.ChangeOutboundWeightFocus -> {
                _outboundWeight.value = outboundWeight.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            outboundWeight.value.text.isBlank()
                )
            }

            is AddEditTicketEvent.SaveTicket -> {
                viewModelScope.launch {
                    try {
                        val licenseNum = licenseNumber.value.text
                        val inbound = inboundWeight.value.text
                        val outbound = outboundWeight.value.text
                        ticketUseCase.addTicket(
                            Ticket(
                                driverName = driverName.value.text,
                                licenseNumber = if (licenseNum.isNotEmpty()) licenseNum.toLong() else 0L,
                                timestamp = timestamp.value,
                                inboundWeight = if (inbound.isNotEmpty()) inbound.toInt() else 0,
                                outboundWeight = if (outbound.isNotEmpty()) outbound.toInt() else 0,
                                id = currentTicketId ?: 0,
                                netWeight = if (inbound.isNotEmpty() && outbound.isNotEmpty())
                                    (outbound.toInt() - inbound.toInt()) else 0
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTicket)
                    } catch (e: InvalidException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save ticket"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveTicket : UiEvent()
    }

    private fun convertTimestampToDateString(timestamp: Long): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val date = Date(timestamp)
        return dateFormat.format(date)
    }
}