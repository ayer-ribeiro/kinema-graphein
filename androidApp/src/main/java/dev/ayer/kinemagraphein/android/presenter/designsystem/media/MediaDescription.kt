package dev.ayer.kinemagraphein.android.presenter.designsystem.media

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MediaDescription(
    description: String,
    lines: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = description,
        textAlign = TextAlign.Left,
        minLines = lines,
        maxLines = lines,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier.padding(8.dp)
    )
}
