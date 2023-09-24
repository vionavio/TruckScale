package android.template.feature.weighbridge.ui.add_edit_ticket

import android.template.feature.weighbridge.ui.Screen
import android.template.feature.weighbridge.ui.add_edit_ticket.components.TransparentHintTextField
import android.template.feature.weighbridge.ui.ticket.components.WeighbridgeAppBar
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditTicketScreen(
    navController: NavController,
    viewModel: AddEditTicketViewModel = hiltViewModel()
) {
    val nameState = viewModel.driverName.value
    val licenseState = viewModel.licenseNumber.value
    val timestampState = viewModel.timestamp.value
    val inboundState = viewModel.inboundWeight.value
    val outboundState = viewModel.outboundWeight.value
    val netWightState = viewModel.netWeight.value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditTicketViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditTicketViewModel.UiEvent.SaveTicket -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            WeighbridgeAppBar(
                icon = Icons.Default.ArrowBack,
                text = "Weighbridge Detail/Edit",
                onIconClick = {
                    navController.navigateUp()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditTicketEvent.SaveTicket)
                },
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save ticket")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .absolutePadding(8.dp, 8.dp, 8.dp, 8.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "Date time : $timestampState")

            TransparentHintTextField(
                text = nameState.text,
                hint = nameState.hint,
                title = nameState.title,
                onValueChange = {
                    viewModel.onEvent(AddEditTicketEvent.EnteredDriverName(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditTicketEvent.ChangeDriverNameFocus(it))
                },
                isHintVisible = nameState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = licenseState.text,
                hint = licenseState.hint,
                title = licenseState.title,
                onValueChange = {
                    val text = it.filter { it.isDigit() }
                    val result = if (text.isEmpty()) 0 else text.toLong()
                    viewModel.onEvent(AddEditTicketEvent.EnteredLicenseNumber(result))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditTicketEvent.ChangeLicenseNumberFocus(it))
                },
                isHintVisible = licenseState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = outboundState.text,
                hint = outboundState.hint,
                title = outboundState.title,
                onValueChange = {
                    val text = it.filter { it.isDigit() }
                    val result = if (text.isEmpty()) 0 else text.toInt()
                    val inbound = if (inboundState.text.isEmpty()) 0 else inboundState.text.toInt()
                    viewModel.onEvent(AddEditTicketEvent.EnteredOutboundWeight(result))
                    viewModel.onEvent(AddEditTicketEvent.NetWeight(
                        result - inbound
                    ))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditTicketEvent.ChangeOutboundWeightFocus(it))
                },
                isHintVisible = outboundState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = inboundState.text,
                hint = inboundState.hint,
                title = inboundState.title,
                onValueChange = {
                    val text = it.filter { it.isDigit() }
                    val result = if (text.isEmpty()) 0 else text.toInt()
                    val outbound = if (outboundState.text.isEmpty()) 0 else outboundState.text.toInt()
                    viewModel.onEvent(AddEditTicketEvent.EnteredInboundWeight(result))
                    viewModel.onEvent(AddEditTicketEvent.NetWeight(
                        outbound - result
                    ))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditTicketEvent.ChangeInboundWeightFocus(it))
                },
                isHintVisible = inboundState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = netWightState.text,
                hint = netWightState.hint,
                title = netWightState.title,
                onValueChange = {
                    viewModel.onEvent(AddEditTicketEvent.NetWeight(it.toInt()))
                },
                onFocusChange = { },
                isHintVisible = false,
                textStyle = MaterialTheme.typography.body1,
                singleLine = true,
            )
        }
    }
}