package com.example.dogceo.ui.core.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dogceo.R
import com.example.dogceo.ui.core.theme.DogCeoTheme

/**
 * Bandeau d'erreur avec un bouton « Fermer ».
 *
 * @param message texte déjà traduit.
 * @param onDismiss appelé au clic sur « Fermer ».
 * @param modifier modificateur appliqué au bandeau.
 */
@Composable
fun ErrorBanner(
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colorScheme.errorContainer,
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp),
        ) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.onErrorContainer,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
            )
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(R.string.error_banner_dismiss))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorBannerPreview() {
    DogCeoTheme {
        ErrorBanner(message = stringResource(R.string.breed_list_error), onDismiss = {})
    }
}
