package android.template.feature.weighbridge.ui.ticket.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.viona.core.domain.util.OrderType
import com.viona.core.domain.util.TicketOrder
import com.viona.noteapp.feature_note.presentation.notes.components.DefaultRadioButton


@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    ticketOrder: TicketOrder = TicketOrder.Date(OrderType.Descending),
    onOrderChange: (TicketOrder) -> Unit
) {

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(text = "Driver Name",
                selected = ticketOrder is TicketOrder.DriverName,
                onSelect = {
                    onOrderChange(TicketOrder.DriverName(ticketOrder.orderType))
                })

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(text = "Date",
                selected = ticketOrder is TicketOrder.Date,
                onSelect = {
                    onOrderChange(TicketOrder.Date(ticketOrder.orderType))
                })

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(text = "License Number",
                selected = ticketOrder is TicketOrder.LicenseNumber,
                onSelect = {
                    onOrderChange(TicketOrder.LicenseNumber(ticketOrder.orderType))
                })

            Spacer(modifier = Modifier.width(12.dp))
        }
        Spacer(modifier = Modifier.width(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            DefaultRadioButton(text = "Ascending",
                selected = ticketOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(ticketOrder.copy(OrderType.Ascending))
                })

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(text = "Descending",
                selected = ticketOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(ticketOrder.copy(OrderType.Descending))
                })
        }

    }
}