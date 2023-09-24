package android.template.feature.weighbridge.ui.ticket

import android.template.feature.weighbridge.ui.Screen
import android.template.feature.weighbridge.ui.ticket.components.OrderSection
import android.template.feature.weighbridge.ui.ticket.components.TicketWeighbridgeItem
import android.template.feature.weighbridge.ui.ticket.components.WeighbridgeAppBar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun TicketScreen(
    navController: NavController,
    viewModel: TicketsWeighbridgeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { WeighbridgeAppBar(
            icon = Icons.Default.Home,
            text = "Weighbridge Ticket"
        ) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditTicketScreen.route)
                },
                containerColor = MaterialTheme.colors.primaryVariant
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Ticket")
            }
        },
        scaffoldState = scaffoldState
    ){ innerPadding ->
        Box(
            modifier = Modifier.background(MaterialTheme.colors.background)
        ){
            Column(
                modifier = Modifier
                    .absolutePadding(8.dp, 8.dp, 8.dp, 8.dp)
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    ticketOrder = state.ticketOrder,
                    onOrderChange = {
                        viewModel.onEvent(TicketWeighbridgeEvent.Order(it))
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.tickets) { ticket ->
                        TicketWeighbridgeItem(
                            ticket = ticket,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        Screen.AddEditTicketScreen.route +
                                                "?ticketId=${ticket.id}"
                                    )
                                },
                            onClick = {
                                viewModel.onEvent(TicketWeighbridgeEvent.DeleteTicket(ticket))
                                scope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Ticket deleted",
                                        actionLabel = "Undo"
                                    )
                                    if(result == SnackbarResult.ActionPerformed) {
                                        viewModel.onEvent(TicketWeighbridgeEvent.RestoreTicket)
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

            }
        }
    }
}