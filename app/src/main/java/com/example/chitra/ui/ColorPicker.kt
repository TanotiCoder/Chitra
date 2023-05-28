package com.example.chitra.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raedapps.alwan.rememberAlwanState
import com.raedapps.alwan.ui.Alwan

@Composable
fun ColorPickerAlertBox(
    sentCurrentColor: (Color) -> Unit,
    onClickDialog: () -> Unit,
    currentColor: Color
) {
    AlertDialog(
        onDismissRequest = {
            onClickDialog()
        },
        modifier = Modifier.padding(16.dp),
        title = {
            Text(text = "Color Picker", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        },
        text = {
            Alwan(
                onColorChanged = { sentCurrentColor(it) },
                state = rememberAlwanState(initialColor = currentColor),
                showAlphaSlider = true,
            )
        },
        buttons = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedButton(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    onClick = { onClickDialog() },
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(text = "Done")
                }
            }
        }
    )
}