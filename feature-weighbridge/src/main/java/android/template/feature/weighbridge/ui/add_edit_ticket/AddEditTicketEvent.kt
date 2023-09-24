package android.template.feature.weighbridge.ui.add_edit_ticket

import androidx.compose.ui.focus.FocusState


sealed class AddEditTicketEvent {
    data class EnteredDriverName(val value: String) : AddEditTicketEvent()
    data class ChangeDriverNameFocus(val focusState: FocusState) : AddEditTicketEvent()
    data class EnteredLicenseNumber(val value: Long) : AddEditTicketEvent()
    data class ChangeLicenseNumberFocus(val focusState: FocusState) : AddEditTicketEvent()
    data class EnteredInboundWeight(val value: Int) : AddEditTicketEvent()
    data class ChangeInboundWeightFocus(val focusState: FocusState) : AddEditTicketEvent()

    data class EnteredOutboundWeight(val value: Int) : AddEditTicketEvent()
    data class NetWeight(val value: Int) : AddEditTicketEvent()
    data class ChangeOutboundWeightFocus(val focusState: FocusState) : AddEditTicketEvent()
    object SaveTicket : AddEditTicketEvent()
}