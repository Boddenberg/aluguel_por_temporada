package JuninWins.Project.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


class NumberUtils {
    companion object {
        fun formatDouble(value: Double): Double {
            val doubleFormatter = DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US)) // Use Locale.US para garantir que o ponto seja o separador decimal
            return doubleFormatter.format(value).toDouble()
        }
    }//TODO: Parece gambi, arrumar outro jeito de fazer isso
}