package dev.ayer.kinemagraphein.android.presenter.designsystem.text

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ayer.kinemagraphein.android.presenter.theme.QuantumTheme

@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Log.d("HomeScreen", "SectionTitle")
    Box (modifier = modifier) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            minLines = 1,
            maxLines = 1,
            modifier = modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }

}

@Composable
@Preview
fun SectionTitlePreview() {
    QuantumTheme {
        SectionTitle(text = "Title")
    }
}
