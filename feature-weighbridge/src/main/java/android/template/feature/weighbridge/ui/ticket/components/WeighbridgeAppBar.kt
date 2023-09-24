package android.template.feature.weighbridge.ui.ticket.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun WeighbridgeAppBar(
    icon: ImageVector,
    text: String,
    onIconClick: () -> Unit = {}
) {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = icon,
                modifier = Modifier
                    .clickable { onIconClick() }
                    .padding(horizontal = 12.dp),
                contentDescription = "Action icon",
            )
        },
        title = { Text(text = text) },
        backgroundColor = MaterialTheme.colors.background
    )
}