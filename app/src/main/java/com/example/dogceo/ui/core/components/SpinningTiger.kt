/*
 * SpinningTiger.kt
 * Couche : UI · Composant partagé
 * Rôle : easter egg, un gros tigre qui tourne par-dessus l'écran.
 * TODO: à réécrire
 */
package com.example.dogceo.ui.core.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.dogceo.R
import com.example.dogceo.ui.core.theme.DogCeoTheme

private const val FULL_TURN_DEGREES = 360f
private const val TURN_DURATION_MILLIS = 1_500

/**
 * Easter egg : un tigre géant qui tourne sans fin au centre d'un fond assombri.
 * Un clic n'importe où le fait disparaître.
 *
 * TODO: à réécrire
 *
 * @param onClose appelé au clic : c'est l'appelant qui cache le tigre.
 * @param modifier pour placer le calque depuis l'écran parent.
 */
@Composable
fun SpinningTiger(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Une transition infinie : Compose recalcule l'angle à chaque image, de 0 à 360°, puis recommence.
    // Elle s'arrête toute seule quand le tigre quitte l'écran.
    // TODO: à réécrire
    val rotation by rememberInfiniteTransition(label = "tiger").animateFloat(
        initialValue = 0f,
        targetValue = FULL_TURN_DEGREES,
        animationSpec = infiniteRepeatable(tween(TURN_DURATION_MILLIS, easing = LinearEasing)),
        label = "tigerRotation",
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.scrim.copy(alpha = 0.5f))
            .clickable(onClickLabel = stringResource(R.string.spinning_tiger_close), onClick = onClose),
    ) {
        Text(
            text = stringResource(R.string.tiger_emoji),
            fontSize = 160.sp,
            modifier = Modifier.rotate(rotation),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SpinningTigerPreview() {
    DogCeoTheme {
        SpinningTiger(onClose = {})
    }
}
