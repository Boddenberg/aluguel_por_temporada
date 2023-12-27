package juninwins.project.exceptions

class AccommodationIdNotFoundException(id: Long) : RuntimeException("Accomodation with ID $id not found!")