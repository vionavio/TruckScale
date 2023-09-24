package android.template.feature.weighbridge.ui.add_edit_ticket.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    title: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(end = 16.dp, start = 16.dp, top = 10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            Column(
                modifier = modifier.padding(start = 8.dp, end = 8.dp)
            ) {
                if (!isHintVisible) {
                    Text(
                        text = title,
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                BasicTextField(

                    value = text,
                    onValueChange = onValueChange,
                    singleLine = singleLine,
                    textStyle = textStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged {
                            onFocusChange(it)
                        },

                    )
            }

            if (isHintVisible) {
                Text(text = hint, style = textStyle, color = Color.DarkGray)
            }
        }
    }
}