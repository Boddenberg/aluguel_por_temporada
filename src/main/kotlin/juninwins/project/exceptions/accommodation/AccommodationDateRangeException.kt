package juninwins.project.exceptions.accommodation

class AccommodationDateRangeException(startDate: String, endDate: String): RuntimeException("This check-in date ($startDate) to ($endDate) is already registered. Please select a different date for your booking reservation.")