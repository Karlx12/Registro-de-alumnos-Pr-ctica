package hrcode.labs.registrodealumnos.ui.theme

import hrcode.labs.registrodealumnos.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp



val HelveticaNeue = FontFamily(
    Font(R.font.helveticaneue_thin, FontWeight.Thin),
    Font(R.font.helveticaneue_light, FontWeight.Light),
    Font(R.font.helveticaneue_roman, FontWeight.Normal),
    Font(R.font.helveticaneue_medium, FontWeight.Medium),
    Font(R.font.helveticaneue_bold, FontWeight.Bold),
    Font(R.font.helveticaneue_black, FontWeight.Black)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = HelveticaNeue,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
