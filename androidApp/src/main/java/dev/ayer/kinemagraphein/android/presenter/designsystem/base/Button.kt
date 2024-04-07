package dev.ayer.kinemagraphein.android.presenter.designsystem.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Button(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    state: ButtonState = ButtonState.Enabled,
    style: ButtonStyle = ButtonStyle.Button,
) {
    Button(
        onClick = onClick,
        state = state,
        style = style,
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        Row(
            modifier = Modifier.padding(0.dp)
        ) {
            if (state == ButtonState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            } else {
                Text(text = text)
            }
        }
    }
}

@Composable
private fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    state: ButtonState = ButtonState.Enabled,
    style: ButtonStyle = ButtonStyle.Button,
    content: @Composable RowScope.() -> Unit,
) {
    val isEnabled = state == ButtonState.Enabled
    val isSelected = state == ButtonState.Selected

    when (style) {
        ButtonStyle.Button -> androidx.compose.material3.Button(
            enabled = isEnabled || isSelected,
            onClick = onClick,
            modifier = modifier,
            content = content,
        )

        ButtonStyle.Outlined -> {
            val colors = if (isSelected) {
                buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.outline,
                    disabledContentColor = MaterialTheme.colorScheme.outlineVariant,
                )
            } else {
                ButtonDefaults.outlinedButtonColors()
            }
            androidx.compose.material3.OutlinedButton(
                enabled = isEnabled || isSelected,
                onClick = onClick,
                modifier = modifier,
                colors = colors,
                content = content
            )
        }

        ButtonStyle.Text -> {
            androidx.compose.material3.TextButton(
                enabled = isEnabled || isSelected,
                onClick = onClick,
                contentPadding = PaddingValues(0.dp),
                modifier = modifier
                    .height(IntrinsicSize.Max)
                    .width(IntrinsicSize.Max),
                shape = RectangleShape,
            ) {
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.Center,
                ) {
                    Row(modifier = modifier.padding(contentPadding)) {
                        content()
                    }

                    if (isSelected) {
                        Divider(
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .matchParentSize()
                                .height(2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ButtonPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            text = "Season 1",
            onClick = {},
            state = ButtonState.Enabled,
            style = ButtonStyle.Button,
        )

        Button(
            text = "Season 1",
            onClick = {},
            state = ButtonState.Disabled,
            style = ButtonStyle.Button,
        )

        Button(
            text = "Season 1",
            onClick = {},
            state = ButtonState.Loading,
            style = ButtonStyle.Button,
        )
        Button(
            text = "Season 1",
            onClick = {},
            state = ButtonState.Selected,
            style = ButtonStyle.Button,
        )
    }
}

@Preview
@Composable
fun OutlinedButtonPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            text = "Season 1",
            onClick = {},
            state = ButtonState.Enabled,
            style = ButtonStyle.Outlined,
        )

        Button(
            text = "Season 1",
            onClick = {},
            state = ButtonState.Disabled,
            style = ButtonStyle.Outlined,
        )

        Button(
            text = "Season 1",
            onClick = {},
            state = ButtonState.Loading,
            style = ButtonStyle.Outlined,
        )
        Button(
            text = "Season 1",
            onClick = {},
            state = ButtonState.Selected,
            style = ButtonStyle.Outlined,
        )
    }
}

@Preview
@Composable
fun TextButtonPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            text = "Season 1",
            onClick = {},
            state = ButtonState.Enabled,
            style = ButtonStyle.Text,
        )

        Button(
            text = "Season 1",
            onClick = {},
            state = ButtonState.Disabled,
            style = ButtonStyle.Text,
        )

        Button(
            text = "Season 1",
            onClick = {},
            state = ButtonState.Loading,
            style = ButtonStyle.Text,
        )
        Button(
            text = "Season 1",
            onClick = {},
            state = ButtonState.Selected,
            style = ButtonStyle.Text,
        )
    }
}