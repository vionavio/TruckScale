package android.template.feature.weighbridge.ui.ticket.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.viona.core.domain.model.Ticket

@Composable
fun TicketWeighbridgeItem(
    ticket: Ticket,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
        ){
            Box(modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .weight(1.5f)
            ) {
                Image(
                    painterResource(android.template.core.ui.R.drawable.ic_truck),
                    modifier = Modifier
                        .size(88.dp)
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                    contentDescription = "Food item thumbnail picture",


                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(4f)
            ) {
                Text(
                    text = ticket.driverName,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = ticket.timestamp,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Light
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "License: ${ticket.licenseNumber.toString()}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Outbound: ${ticket.outboundWeight}  Inbound: ${ticket.inboundWeight}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Net Weight: ${ticket.netWeight}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )
            }
            Box(
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            ) {
                IconButton(
                    onClick = onClick,
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        tint = Color.LightGray,
                        contentDescription = "Ticket Clicked"

                    )
                }
            }
        }
    }
}