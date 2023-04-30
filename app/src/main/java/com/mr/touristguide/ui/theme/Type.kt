package com.mr.touristguide.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val h1 = SpanStyle(
    fontSize = 22.sp,
)
val italicTitle = TextStyle(
    fontSize = 22.sp,
    fontStyle = FontStyle.Italic
)
val h2 = SpanStyle(
    fontSize = 20.sp
)
val textSpanNormal = SpanStyle(
    fontSize = 18.sp
)
val textNormal = TextStyle(
    fontSize = 16.sp
)
val textSmall = TextStyle(
    fontSize = 14.sp
)
val textLarge = TextStyle(
    fontSize = 18.sp
)
val textLargeItalic = TextStyle(
    fontSize = 18.sp,
    fontStyle = FontStyle.Italic
)
val titleLargeItalic = TextStyle(
    fontSize = 40.sp,
    fontStyle = FontStyle.Italic
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/
)