package juninwins.project.exceptions.accommodation

class AccommodationAlreadyRegisteredException (key: String): RuntimeException("Accommodation Key $key is already registered!")