package com.scapricorne.core.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.scapricorne.core.ui.R

val font = FontFamily(
    Font(R.font.dosis_regular),
    Font(R.font.dosis_light, FontWeight.Light),
    Font(R.font.dosis_medium, FontWeight.Medium),
    Font(R.font.dosis_bold, FontWeight.Bold),
    Font(R.font.dosis_semibold, FontWeight.SemiBold)
)


val Typography = Typography(defaultFontFamily = font)