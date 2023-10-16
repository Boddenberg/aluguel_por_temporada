package JuninWins.Project.utils

import java.text.*
import java.util.*

fun formatDouble(value: Double): Double {
    return DecimalFormat("#.##", DecimalFormatSymbols(Locale.US)).format(value).toDouble()
}