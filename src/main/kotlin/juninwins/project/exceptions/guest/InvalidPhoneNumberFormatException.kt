package juninwins.project.exceptions.guest


class InvalidPhoneNumberFormatException(phoneNumber: String): RuntimeException("Phone number $phoneNumber is invalid")