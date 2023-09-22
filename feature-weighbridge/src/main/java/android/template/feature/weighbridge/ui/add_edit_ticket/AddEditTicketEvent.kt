package android.template.feature.weighbridge.ui.add_edit_ticket

import androidx.compose.ui.focus.FocusState


sealed class AddEditTicketEvent {
    data class EnteredDriverName(val value: String) : AddEditTicketEvent()
    data class ChangeDriverNameFocus(val focusState: FocusState) : AddEditTicketEvent()
    data class EnteredLicenseNumber(val value: Int) : AddEditTicketEvent()
    data class ChangeLicenseNumberFocus(val focusState: FocusState) : AddEditTicketEvent()

    data class EnteredInboundWeight(val value: Double) : AddEditTicketEvent()
    data class ChangeInboundWeightFocus(val focusState: FocusState) : AddEditTicketEvent()

    data class EnteredOutboundWeight(val value: Double) : AddEditTicketEvent()
    data class ChangeOutboundWeightFocus(val focusState: FocusState) : AddEditTicketEvent()
    object SaveTicket : AddEditTicketEvent()
}