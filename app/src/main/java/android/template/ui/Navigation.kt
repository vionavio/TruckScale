/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.template.ui

import android.template.feature.weighbridge.ui.Screen
import android.template.feature.weighbridge.ui.add_edit_ticket.AddEditTicketScreen
import android.template.feature.weighbridge.ui.ticket.TicketScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MainNavigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.TicketsScreen.route
    ) {
        composable(route = Screen.TicketsScreen.route) {
            TicketScreen(navController = navController)
        }
        composable(
            route = Screen.AddEditTicketScreen.route +
                    "?ticketId={ticketId}",
            arguments = listOf(
                navArgument(
                    name = "ticketId"
                ) {
                    type = NavType.StringType
                    defaultValue = "-1"
                },
            )
        ) {
            AddEditTicketScreen(
                navController = navController
            )
        }
    }
}
