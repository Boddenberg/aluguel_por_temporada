package juninwins.project.exceptions.accommodation

class AccommodationIdNotFoundException(id: Long) : RuntimeException("Accomodation with ID $id not found!")