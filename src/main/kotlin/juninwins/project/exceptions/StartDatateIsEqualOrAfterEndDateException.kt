package juninwins.project.exceptions

class StartDatateIsEqualOrAfterEndDateException(startDate: String): RuntimeException("Start Date to booking is after than end date, please check date: $startDate  for register new booking")