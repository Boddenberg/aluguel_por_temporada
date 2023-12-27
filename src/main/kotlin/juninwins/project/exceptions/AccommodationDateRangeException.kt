package juninwins.project.exceptions

class AccommodationDateRangeException(startDate: String, endDate: String): RuntimeException("This check-in date ($startDate) to ($endDate) is already registered. Please select a different date for your booking reservation.")