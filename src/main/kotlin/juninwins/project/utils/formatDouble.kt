package juninwins.project.utils

import java.text.*
import java.util.*

fun formatDouble(value: Double): Double {
    return DecimalFormat("#.##", DecimalFormatSymbols(Locale.US)).format(value).toDouble()
}