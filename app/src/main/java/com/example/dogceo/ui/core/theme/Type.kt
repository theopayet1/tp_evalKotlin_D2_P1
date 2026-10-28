/*
 * Type.kt
 * Couche : UI · Thème
 * Rôle : styles de texte de l'app.
 * TODO: à réécrire
 */
package com.example.dogceo.ui.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Styles de texte Material de l'app
// TODO: à réécrire
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)