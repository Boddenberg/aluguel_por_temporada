package juninwins.project.utils

import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.NumberParseException
import juninwins.project.exceptions.guest.InvalidPhoneNumberFormatException

private fun isValidPhoneNumber(phoneNumber: String, region: String = "BR"): Boolean {
    val phoneUtil = PhoneNumberUtil.getInstance()
    return try {
        val parsedNumber = phoneUtil.parse(phoneNumber, region)
        phoneUtil.isValidNumber(parsedNumber)
    } catch (e: NumberParseException) {
        throw InvalidPhoneNumberFormatException(phoneNumber)
    }
}

private fun formatPhoneNumber(number: String): String {
    val phoneUtil = PhoneNumberUtil.getInstance()
        val parsedNumber = phoneUtil.parse(number, "BR")
        return phoneUtil.format(parsedNumber, PhoneNumberUtil.PhoneNumberFormat.E164)
}

fun validateAndFormatPhoneNumber(number: String, region: String = "BR"): String {
    isValidPhoneNumber(number, region)
    return formatPhoneNumber(number)
}