package juninwins.project.exceptions.accommodation

class AccommodationIdNotFoundException(id: String) : RuntimeException("Accomodation with ID $id not found!")