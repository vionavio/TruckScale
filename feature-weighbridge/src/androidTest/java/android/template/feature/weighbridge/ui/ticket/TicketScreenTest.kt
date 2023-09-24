package android.template.feature.weighbridge.ui.ticket

import android.template.core.ui.MyApplicationTheme
import android.template.feature.weighbridge.ui.Screen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TicketScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.runOnUiThread {
            composeRule.activity.setContent {
                val navController = rememberNavController()
                MyApplicationTheme {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TicketsScreen.route
                    ) {
                        composable(route = Screen.TicketsScreen.route) {
                            TicketScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }

    @Test
    fun page_isVisible() {
        composeRule.onNodeWithText(FAKE_DATA.first()).assertExists().performClick()
    }
}
private val FAKE_DATA = listOf("Driver Name", "Date")